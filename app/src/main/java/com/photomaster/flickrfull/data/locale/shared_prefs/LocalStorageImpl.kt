package com.photomaster.flickrfull.data.locale.shared_prefs

import android.app.Application
import android.content.Context.MODE_PRIVATE
import com.googlecode.flickrjandroid.oauth.OAuth
import com.photomaster.flickrfull.di.app.PerApplication
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

private const val SHARED_NAME = "sharedPrefs"

@PerApplication
class LocalStorageImpl @Inject constructor(private val application: Application) : LocalStorage {

    override fun writeTo(
        key: String,
        value: String
    ) {
        application.getSharedPreferences(SHARED_NAME, MODE_PRIVATE).edit().putString(key, value)
            .apply()
    }

    override fun readFrom(key: String): String {
        return application.getSharedPreferences(SHARED_NAME, MODE_PRIVATE)
            .getString(key, "")!!

    }

    fun writeToAsync(
        key: String,
        value: OAuth
    ): Completable {
        return Completable.fromAction {
            application.getSharedPreferences(SHARED_NAME, MODE_PRIVATE).edit()
                .putString(key, OAuthConverter.toJson(oAuth = value)).apply()
        }
    }

    fun readFromAsync(key: String): Maybe<OAuth> {
        return Maybe.fromCallable {
            OAuthConverter.fromJson(
                application.getSharedPreferences(SHARED_NAME, MODE_PRIVATE)
                    .getString(key, "")!!
            )
        }
    }
}
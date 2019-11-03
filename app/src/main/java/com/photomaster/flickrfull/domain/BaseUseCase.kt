package com.photomaster.flickrfull.domain

import com.googlecode.flickrjandroid.oauth.OAuthToken
import com.photomaster.flickrfull.utils.OAUTH_SECRET_TOKEN_KEY
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase {

    fun execute(
        completable: Completable,
        observer: CompletableObserver
    ) {
        completable
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(observer)
    }

    fun execute(
        single: Single<OAuthToken>,
        observer: SingleObserver<OAuthToken>
    ) {
        single
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(observer)
    }

    fun execute(completable: Completable, function: () -> Unit): Disposable {
        return completable
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(function)
    }

    abstract fun dispose()
}


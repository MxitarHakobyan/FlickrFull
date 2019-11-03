package com.photomaster.flickrfull.ui.main

import android.os.Bundle
import android.util.Log
import com.photomaster.flickrfull.R
import com.photomaster.flickrfull.data.locale.shared_prefs.LocalStorageImpl
import com.photomaster.flickrfull.utils.OAUTH_KEY
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.functions.Consumer
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    private val TAG = "MainActivity"

    @Inject
    lateinit var localStorageImpl: LocalStorageImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        localStorageImpl.readFromAsync(OAUTH_KEY)
            .subscribe(Consumer {  Log.d(TAG, it.toString())})
    }
}

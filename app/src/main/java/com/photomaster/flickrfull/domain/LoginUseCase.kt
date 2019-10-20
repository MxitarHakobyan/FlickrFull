package com.photomaster.flickrfull.domain

import com.photomaster.flickrfull.di.login.PerLogin
import com.photomaster.flickrfull.oauth.FlickrClient
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.net.URL
import javax.inject.Inject

@PerLogin
class LoginUseCase @Inject constructor(
    private val flickrClient: FlickrClient,
    private val compositeDisposable: CompositeDisposable
) : BaseUseCase() {

    private val TAG = "LoginUseCase"

    private val urlSubject = PublishSubject.create<URL>()

    fun authorize() {
        compositeDisposable.add(
            execute(Completable.fromAction {}
            ) { urlSubject.onNext(flickrClient.getAuthorizationResponse(flickrClient.getOAuthInterface())) }
        )
    }

    fun getUrlSubject() = urlSubject.hide()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun dispose() {
        compositeDisposable.dispose()
    }
}
package com.photomaster.flickrfull.domain

import com.googlecode.flickrjandroid.oauth.OAuth
import com.googlecode.flickrjandroid.oauth.OAuthToken
import com.photomaster.flickrfull.di.login.PerLogin
import com.photomaster.flickrfull.oauth.FlickrClient
import io.reactivex.Completable
import io.reactivex.Observable
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
    private val oAuthSubject = PublishSubject.create<OAuth>()
    private val oAuthTokenSubject = PublishSubject.create<OAuthToken>()

    fun authorize() {
        compositeDisposable.add(
            execute(Completable.fromAction {}
            ) {
                val oAuthToken: OAuthToken = flickrClient.getOAuthInterface()
                oAuthTokenSubject.onNext(oAuthToken)
                urlSubject.onNext(flickrClient.getAuthorizationResponse(oAuthToken))
            }
        )
    }

    fun getOAuthTokenObservable(): Observable<OAuthToken> = oAuthTokenSubject.hide()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getUrlObservable(): Observable<URL> = urlSubject.hide()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getAccessToken(
        token: String,
        tokenSecret: String,
        oauthVerifier: String
    ) {
        compositeDisposable.add(
            execute(Completable.fromAction {}) {
                oAuthSubject.onNext(flickrClient.getAccessToken(token, tokenSecret, oauthVerifier))
            }
        )
    }

    fun getOAuthObservable(): Observable<OAuth> = oAuthSubject.hide()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun dispose() {
        compositeDisposable.dispose()
    }
}
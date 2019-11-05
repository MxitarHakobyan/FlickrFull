package com.photomaster.flickrfull.domain.login

import com.googlecode.flickrjandroid.oauth.OAuth
import com.googlecode.flickrjandroid.oauth.OAuthToken
import com.photomaster.flickrfull.data.locale.shared_prefs.LocalStorageImpl
import com.photomaster.flickrfull.di.login.PerLogin
import com.photomaster.flickrfull.domain.common.BaseUseCase
import com.photomaster.flickrfull.oauth.FlickrClient
import com.photomaster.flickrfull.utils.OAUTH_KEY
import com.photomaster.flickrfull.utils.OAUTH_SECRET_TOKEN_KEY
import com.photomaster.flickrfull.utils.OAUTH_TOKEN_KEY
import com.photomaster.flickrfull.utils.USER_ID_KEY
import io.reactivex.Completable
import io.reactivex.MaybeObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.net.URL
import javax.inject.Inject

@PerLogin
class LoginUseCase @Inject constructor(
    private val flickrClient: FlickrClient,
    private val localStorage: LocalStorageImpl,
    private val compositeDisposable: CompositeDisposable
) : BaseUseCase() {

    private val TAG = "LoginUseCase"

    private val urlSubject = PublishSubject.create<URL>()
    private val oAuthSubject = PublishSubject.create<OAuth>()
    private val oAuthTokenSubject = PublishSubject.create<OAuthToken>()
    private val isStored = PublishSubject.create<Boolean>()
    private val haveOauthStored = PublishSubject.create<Boolean>()

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

    fun getOAuthTokenSecret(): String = localStorage.readFrom(OAUTH_SECRET_TOKEN_KEY)

    fun storeOAuth(oAuth: OAuth): Observable<Boolean> {
        compositeDisposable.add(
            localStorage.writeToAsync(OAUTH_KEY, oAuth)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { isStored.onNext(true) }
        )
        return isStored.hide()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun oAuthFromStore(): Observable<Boolean> {
        localStorage.readFromAsync(OAUTH_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(object : MaybeObserver<OAuth> {
                override fun onSuccess(t: OAuth) {
                    haveOauthStored.onNext(true)
                    storeInShared(OAUTH_TOKEN_KEY, t.token.oauthToken)
                    storeInShared(USER_ID_KEY, t.user.id)
                }

                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    haveOauthStored.onNext(false)
                }
            })
        return haveOauthStored.hide()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun storeInShared(key: String, oAuthSecret: String) {
        localStorage.writeTo(key, oAuthSecret)
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}
package com.photomaster.flickrfull.domain.main

import com.googlecode.flickrjandroid.oauth.OAuth
import com.photomaster.flickrfull.data.locale.shared_prefs.LocalStorageImpl
import com.photomaster.flickrfull.di.main.PerMain
import com.photomaster.flickrfull.domain.common.BaseUseCase
import com.photomaster.flickrfull.utils.OAUTH_KEY
import io.reactivex.MaybeObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@PerMain
class MainUseCase @Inject constructor(
    private val localStorage: LocalStorageImpl,
    private val compositeDisposable: CompositeDisposable
) : BaseUseCase() {

    private val oAuthSubject: PublishSubject<OAuth> = PublishSubject.create()

    fun oAuthFromStore(): Observable<OAuth> {
        localStorage.readFromAsync(OAUTH_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(object : MaybeObserver<OAuth> {
                override fun onSuccess(t: OAuth) {
                    oAuthSubject.onNext(t)
                }

                override fun onComplete() {
                    oAuthSubject.onComplete()
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    oAuthSubject.onError(e)
                }
            })
        return oAuthSubject.hide()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun storeInShared(
        key: String,
        userId: String
    ) {
        localStorage.writeTo(key, userId)
    }

    fun getFromStore(key: String): String = localStorage.readFrom(key)

    override fun dispose() {
        compositeDisposable.dispose()
    }
}
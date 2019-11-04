package com.photomaster.flickrfull.domain.common

import com.photomaster.flickrfull.data.remote.entity.HeadOfResponse
import io.reactivex.*
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
        observable: Flowable<HeadOfResponse>,
        observer: FlowableSubscriber<HeadOfResponse>
    ) {
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(observer)
    }

    fun execute(
        single: Single<Any>,
        observer: SingleObserver<Any>
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


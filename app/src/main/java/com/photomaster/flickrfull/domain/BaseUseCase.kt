package com.photomaster.flickrfull.domain

import com.photomaster.flickrfull.data.remote.entity.PostEntity
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Maybe
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase {

    fun execute(
        completable: Completable,
        observer: CompletableObserver
    ) {
        completable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    fun execute(
        maybe: Maybe<List<PostEntity>>,
        observer: MaybeObserver<List<PostEntity>>
    ) {
        maybe
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    abstract fun dispose()
}
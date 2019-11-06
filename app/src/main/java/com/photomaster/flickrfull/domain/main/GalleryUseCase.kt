package com.photomaster.flickrfull.domain.main

import android.util.Log
import com.photomaster.flickrfull.data.remote.entity.HeadOfResponse
import com.photomaster.flickrfull.data.repos.GalleryRepository
import com.photomaster.flickrfull.di.app.PerApplication
import com.photomaster.flickrfull.domain.common.BaseUseCase
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerApplication
class GalleryUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val compositeDisposable: CompositeDisposable
) :
    BaseUseCase() {

    fun getGalleries(userId: String): Flowable<HeadOfResponse> =
        galleryRepository.getGalleries(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun createGallery(title: String, description: String) {
        execute(galleryRepository.createGallery(title, description, "", ""),
            object : CompletableObserver {
                override fun onComplete() {
                    Log.d("create", "completed")
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.d("create", "Error ${e.message}")

                }
            })
    }

    override fun dispose() {
        compositeDisposable.clear()
    }
}

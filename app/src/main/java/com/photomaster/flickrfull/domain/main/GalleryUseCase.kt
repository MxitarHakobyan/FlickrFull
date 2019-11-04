package com.photomaster.flickrfull.domain.main

import android.util.Log
import com.photomaster.flickrfull.data.remote.entity.HeadOfResponse
import com.photomaster.flickrfull.data.repos.GalleryRepository
import com.photomaster.flickrfull.di.app.PerApplication
import com.photomaster.flickrfull.domain.common.BaseUseCase
import io.reactivex.FlowableSubscriber
import io.reactivex.disposables.CompositeDisposable
import org.reactivestreams.Subscription
import javax.inject.Inject

@PerApplication
class GalleryUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val compositeDisposable: CompositeDisposable
) :
    BaseUseCase() {

    private val TAG = "GalleryUseCase"

    private lateinit var subscription: Subscription


    fun getGalleries() {
        execute(observable = galleryRepository.getGalleries(),
            observer = object : FlowableSubscriber<HeadOfResponse> {
                override fun onComplete() {

                }

                override fun onSubscribe(s: Subscription) {
                    subscription = s
                }

                override fun onNext(t: HeadOfResponse) {
                    Log.d(TAG, t.toString())
                }

                override fun onError(t: Throwable) {

                }

            })
    }

    override fun dispose() {
        subscription.cancel()
    }
}
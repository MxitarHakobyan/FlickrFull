package com.photomaster.flickrfull.domain.main

import com.photomaster.flickrfull.data.remote.entity.HeadOfResponse
import com.photomaster.flickrfull.data.repos.GalleryRepository
import com.photomaster.flickrfull.di.app.PerApplication
import com.photomaster.flickrfull.domain.common.BaseUseCase
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerApplication
class GalleryUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val compositeDisposable: CompositeDisposable
) :
    BaseUseCase() {

    fun getGalleries(userId: String): Flowable<HeadOfResponse> =
        galleryRepository.getGalleries(userId)

    override fun dispose() {
        compositeDisposable.clear()
    }
}
package com.photomaster.flickrfull.domain

import com.photomaster.flickrfull.data.repos.GalleryRepository
import com.photomaster.flickrfull.di.app.PerApplication
import com.photomaster.flickrfull.domain.common.BaseUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerApplication
class PostUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val compositeDisposable: CompositeDisposable
) : BaseUseCase() {

    override fun dispose() {
        compositeDisposable.dispose()
    }
}

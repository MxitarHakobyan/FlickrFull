package com.photomaster.flickrfull.data.repos

import com.photomaster.flickrfull.data.remote.FlickrApi
import com.photomaster.flickrfull.data.remote.entity.HeadOfResponse
import com.photomaster.flickrfull.di.app.PerApplication
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

@PerApplication
class GalleryRepository @Inject constructor(private val api: FlickrApi) {

    fun getGalleries(userId: String): Flowable<HeadOfResponse> = api.getGalleries(userId)

    fun createGallery(
        title: String,
        description: String,
        photoId: String,
        fullResult: String
    ): Completable = api.createGallery(title, description, photoId, fullResult)
}
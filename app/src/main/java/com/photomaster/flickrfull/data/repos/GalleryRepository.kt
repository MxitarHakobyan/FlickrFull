package com.photomaster.flickrfull.data.repos

import com.photomaster.flickrfull.data.remote.FlickrApi
import com.photomaster.flickrfull.data.remote.entity.HeadOfResponse
import com.photomaster.flickrfull.di.app.PerApplication
import io.reactivex.Flowable
import javax.inject.Inject

@PerApplication
class GalleryRepository @Inject constructor(private val api: FlickrApi) {
    fun getGalleries(): Flowable<HeadOfResponse> = api.getGalleries()
}
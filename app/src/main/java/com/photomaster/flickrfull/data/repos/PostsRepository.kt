package com.photomaster.flickrfull.data.repos

import com.photomaster.flickrfull.data.remote.FlickrApi
import com.photomaster.flickrfull.di.app.PerApplication
import javax.inject.Inject

@PerApplication
class PostsRepository @Inject constructor(private val api: FlickrApi) {

}
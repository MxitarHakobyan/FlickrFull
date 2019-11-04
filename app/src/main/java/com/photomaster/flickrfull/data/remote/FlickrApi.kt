package com.photomaster.flickrfull.data.remote

import com.photomaster.flickrfull.data.remote.entity.HeadOfResponse
import com.photomaster.flickrfull.utils.CONSUMER_KEY
import io.reactivex.Flowable
import retrofit2.http.GET

interface FlickrApi {

    //https://www.flickr.com/services/rest/
    // ?method=flickr.galleries.getList
    // &api_key=f0ba267d7ad25a32cb67fab3494fbc83
    // &format=rest
    // &auth_token=72157711636199703-b04bf8000202ec9f
    // &api_sig=219fbe25534a8aaa4062c91061915489
    @GET("rest/" +
            "?method=flickr.galleries.getList" +
            "&api_key=$CONSUMER_KEY" +
            "&format=rest")
    fun getGalleries(): Flowable<HeadOfResponse>

}
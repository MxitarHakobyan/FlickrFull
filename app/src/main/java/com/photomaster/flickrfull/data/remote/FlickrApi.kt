package com.photomaster.flickrfull.data.remote

import com.photomaster.flickrfull.data.remote.entity.HeadOfResponse
import com.photomaster.flickrfull.utils.CONSUMER_KEY
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.http.*

interface FlickrApi {

    @GET(
        "rest/" +
                "?method=flickr.galleries.getList" +
                "&api_key=$CONSUMER_KEY" +
                "&format=json" +
                "&nojsoncallback=1"
    )
    fun getGalleries(
        @Query("user_id") userId: String
    ): Flowable<HeadOfResponse>

    @FormUrlEncoded
    @POST(
        "rest/" +
                "?method=flickr.galleries.create" +
                "&api_key=$CONSUMER_KEY" +
                "&format=json" +
                "&nojsoncallback=1"
    )
    fun createGallery(
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("primary_photo_id") photoId: String,
        @Field("full_result") fullResult: String
    ) : Completable
}
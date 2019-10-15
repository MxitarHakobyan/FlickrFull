package com.photomaster.flickrfull.data.remote

import com.photomaster.flickrfull.data.remote.entity.PostEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import retrofit2.http.*

interface JsonPlaceHolderApi {

    @GET("/posts")
    fun getPostsList(): Maybe<List<PostEntity>>

    @GET("/posts")
    fun getPostsById(@Query("userId") id: Int): Maybe<List<PostEntity>>

    @FormUrlEncoded
    @PUT("/posts/1")
    fun putPost(
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("userId") userId: Int
    ): Completable

    @DELETE("/posts/{id}")
    fun deletePost(@Path("id") id: Int): Completable
}
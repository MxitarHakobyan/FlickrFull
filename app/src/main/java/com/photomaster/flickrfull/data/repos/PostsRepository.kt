package com.photomaster.flickrfull.data.repos

import com.photomaster.flickrfull.data.remote.JsonPlaceHolderApi
import com.photomaster.flickrfull.data.remote.entity.PostEntity
import com.photomaster.flickrfull.di.app.PerApplication
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

@PerApplication
class PostsRepository @Inject constructor(private val api: JsonPlaceHolderApi) {

    fun getPostsList(): Maybe<List<PostEntity>> = api.getPostsList()

    fun getPostsById(id: Int): Maybe<List<PostEntity>> = api.getPostsById(id)

    fun putPost(id: Int,
                title: String,
                body: String,
                userId: Int): Completable = api.putPost(id, title, body, userId)

    fun deletePost(id: Int): Completable = api.deletePost(id)
}
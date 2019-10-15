package com.photomaster.flickrfull.domain

import android.util.Log
import com.photomaster.flickrfull.data.repos.PostsRepository
import com.photomaster.flickrfull.di.app.PerApplication
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerApplication
class PostUseCase @Inject constructor(private val postsRepository: PostsRepository) {

    fun getPostsList() {
        val disposable = postsRepository.getPostsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list -> print("himarrrrrrrrrrrrrrr $list") }
    }

    fun getPostsById(id: Int) {
        val disposable = postsRepository.getPostsById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list -> print("++++++++++++++++++ $list") }
    }

    fun putPost(
        id: Int,
        title: String,
        body: String,
        userId: Int
    ) {
        val disposable = postsRepository.putPost(id, title, body, userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    Log.d("+++++++++", "chishta anasun")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    Log.d("+++++++++", "chishta anasun ${e.message}")
                }

            })
    }

    fun deletePost(id: Int) {
        val disposable = postsRepository.deletePost(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    Log.d("-----------", "chishta anasun")

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    Log.d("----------", "chishta anasun ${e.message}")

                }
            }
            )
    }
}
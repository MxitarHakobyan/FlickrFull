package com.photomaster.flickrfull.domain

import android.util.Log
import com.photomaster.flickrfull.data.remote.entity.PostEntity
import com.photomaster.flickrfull.data.repos.PostsRepository
import com.photomaster.flickrfull.di.app.PerApplication
import io.reactivex.CompletableObserver
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerApplication
class PostUseCase @Inject constructor(
    private val postsRepository: PostsRepository,
    private val compositeDisposable: CompositeDisposable
) : BaseUseCase() {

    fun getPostsList() {
        execute(
            maybe = postsRepository.getPostsList(),
            observer = object : MaybeObserver<List<PostEntity>> {
                override fun onSuccess(posts: List<PostEntity>) {
                    Log.d("++++++", "All posts = $posts")
                }

                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.d("++++++", "Error = ${e.message}")
                }
            })
    }

    fun getPostsById(id: Int) {
        execute(
            maybe = postsRepository.getPostsById(id),
            observer = object : MaybeObserver<List<PostEntity>> {
                override fun onSuccess(posts: List<PostEntity>) {
                    Log.d("++++++", "All posts with id:$id = $posts")
                }

                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.d("++++++", "Error  = ${e.message}")
                }
            })
    }

    fun putPost(
        id: Int,
        title: String,
        body: String,
        userId: Int
    ) {
        execute(
            completable = postsRepository.putPost(id, title, body, userId),
            observer = object : CompletableObserver {
                override fun onComplete() {
                    Log.d("+++++++++", "Request completed")
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.d("++++++", "Request error  = ${e.message}")

                }

            })
    }

    fun deletePost(id: Int) {
        execute(
            completable = postsRepository.deletePost(id),
            observer = object : CompletableObserver {
                override fun onComplete() {
                    Log.d("--------", "Request completed")
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.d("-------", "Request error  = ${e.message}")

                }
            })
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}

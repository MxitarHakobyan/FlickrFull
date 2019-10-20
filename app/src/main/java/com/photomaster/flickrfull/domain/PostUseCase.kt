package com.photomaster.flickrfull.domain

import com.photomaster.flickrfull.data.repos.PostsRepository
import com.photomaster.flickrfull.di.app.PerApplication
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerApplication
class PostUseCase @Inject constructor(
    private val postsRepository: PostsRepository,
    private val compositeDisposable: CompositeDisposable
) : BaseUseCase() {

    override fun dispose() {
        compositeDisposable.dispose()
    }
}

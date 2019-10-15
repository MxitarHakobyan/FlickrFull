package com.photomaster.flickrfull.ui

import android.os.Bundle
import com.photomaster.flickrfull.R
import com.photomaster.flickrfull.domain.PostUseCase
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var postUseCase: PostUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postUseCase.getPostsList()
        postUseCase.getPostsById(1)
        postUseCase.putPost(1, "vochxar", "tavar", 12)
        postUseCase.deletePost(1)
    }
}

package com.photomaster.flickrfull.di.login

import com.googlecode.flickrjandroid.Flickr
import com.photomaster.flickrfull.utils.CONSUMER_KEY
import com.photomaster.flickrfull.utils.CONSUMER_SECRET
import dagger.Module
import dagger.Provides

@Module
abstract class LoginModule {

    @Module
    companion object {

        @JvmStatic
        @PerLogin
        @Provides
        fun provideFlickr() = Flickr(
            CONSUMER_KEY,
            CONSUMER_SECRET
        )

    }
}
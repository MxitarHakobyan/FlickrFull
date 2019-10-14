package com.photomaster.flickrfull.di.app

import com.photomaster.flickrfull.data.remote.FlickrApi
import com.photomaster.flickrfull.utils.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @Module
    companion object {

        @JvmStatic
        @PerApplication
        @Provides
        fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        @JvmStatic
        @PerApplication
        @Provides
        fun provideFlickrApi(retrofit: Retrofit) {
            retrofit.create(FlickrApi::class.java)
        }
    }
}
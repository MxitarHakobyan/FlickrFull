package com.photomaster.flickrfull.di.app

import com.photomaster.flickrfull.data.remote.JsonPlaceHolderApi
import com.photomaster.flickrfull.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class AppModule {

    @Module
    companion object {

        @JvmStatic
        @PerApplication
        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor();
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

        @JvmStatic
        @PerApplication
        @Provides
        fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
            OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        @JvmStatic
        @PerApplication
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        @JvmStatic
        @PerApplication
        @Provides
        fun provideJsonPlaceHolderApi(retrofit: Retrofit): JsonPlaceHolderApi =
            retrofit.create(JsonPlaceHolderApi::class.java)

        @JvmStatic
        @Provides
        fun provideCompositeDisposable() : CompositeDisposable = CompositeDisposable()
    }
}
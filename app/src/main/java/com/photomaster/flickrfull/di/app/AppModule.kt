package com.photomaster.flickrfull.di.app

import com.photomaster.flickrfull.data.locale.shared_prefs.LocalStorageImpl
import com.photomaster.flickrfull.data.remote.FlickrApi
import com.photomaster.flickrfull.utils.BASE_URL
import com.photomaster.flickrfull.utils.OAUTH_TOKEN_KEY
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
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
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

        @JvmStatic
        @PerApplication
        @Provides
        fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            localStorageImpl: LocalStorageImpl
        ) =
            OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(object : Interceptor {

                    override fun intercept(chain: Interceptor.Chain): Response {
                        chain.connection()
                        val original = chain.request()
                        val request = original.newBuilder()
                            .header("auth_token", localStorageImpl.readFrom(OAUTH_TOKEN_KEY))
                            .method(original.method, original.body)
                            .build()

                        return chain.proceed(request)
                    }
                })
                .build()

        @JvmStatic
        @PerApplication
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        @JvmStatic
        @PerApplication
        @Provides
        fun provideJsonPlaceHolderApi(retrofit: Retrofit): FlickrApi =
            retrofit.create(FlickrApi::class.java)

        @JvmStatic
        @Provides
        fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
    }
}
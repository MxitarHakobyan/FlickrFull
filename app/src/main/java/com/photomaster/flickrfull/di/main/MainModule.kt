package com.photomaster.flickrfull.di.main

import androidx.lifecycle.ViewModel
import com.photomaster.flickrfull.data.locale.shared_prefs.LocalStorageImpl
import com.photomaster.flickrfull.data.remote.FlickrApi
import com.photomaster.flickrfull.di.app.PerApplication
import com.photomaster.flickrfull.di.app.ViewModelKey
import com.photomaster.flickrfull.ui.main.MainViewModel
import com.photomaster.flickrfull.utils.BASE_URL
import com.photomaster.flickrfull.utils.OAUTH_TOKEN_KEY
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}
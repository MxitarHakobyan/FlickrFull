package com.photomaster.flickrfull.di.login

import androidx.lifecycle.ViewModel
import com.googlecode.flickrjandroid.Flickr
import com.photomaster.flickrfull.di.app.PerApplication
import com.photomaster.flickrfull.di.app.ViewModelKey
import com.photomaster.flickrfull.ui.login.LoginViewModel
import com.photomaster.flickrfull.utils.CONSUMER_KEY
import com.photomaster.flickrfull.utils.CONSUMER_SECRET
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {

//    @Module
//    companion object {
//
//        @JvmStatic
//        @PerLogin
//        @Provides
//        fun provideFlickr() = Flickr(
//            CONSUMER_KEY,
//            CONSUMER_SECRET
//        )
//    }

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}
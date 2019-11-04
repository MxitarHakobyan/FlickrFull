package com.photomaster.flickrfull.di.app

import com.photomaster.flickrfull.di.login.LoginModule
import com.photomaster.flickrfull.di.login.PerLogin
import com.photomaster.flickrfull.di.main.MainModule
import com.photomaster.flickrfull.di.main.PerMain
import com.photomaster.flickrfull.ui.login.LoginActivity
import com.photomaster.flickrfull.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @PerLogin
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @PerMain
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
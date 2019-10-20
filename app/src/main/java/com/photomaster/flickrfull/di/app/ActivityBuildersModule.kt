package com.photomaster.flickrfull.di.app

import com.photomaster.flickrfull.di.login.LoginModule
import com.photomaster.flickrfull.di.login.PerLogin
import com.photomaster.flickrfull.di.main.PerMain
import com.photomaster.flickrfull.ui.login.LoginActivity
import com.photomaster.flickrfull.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @PerLogin
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @PerMain
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}
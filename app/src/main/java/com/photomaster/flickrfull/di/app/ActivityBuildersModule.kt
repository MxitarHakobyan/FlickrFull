package com.photomaster.flickrfull.di.app

import com.photomaster.flickrfull.di.main.PerMain
import com.photomaster.flickrfull.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @PerMain
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}
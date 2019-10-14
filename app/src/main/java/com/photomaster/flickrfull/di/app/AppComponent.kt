package com.photomaster.flickrfull.di.app

import android.app.Application
import com.photomaster.flickrfull.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@PerApplication
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class
])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
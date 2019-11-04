package com.photomaster.flickrfull.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.googlecode.flickrjandroid.oauth.OAuth
import com.photomaster.flickrfull.domain.main.GalleryUseCase
import com.photomaster.flickrfull.domain.main.MainUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val mainUseCase: MainUseCase,
    private val galleryUseCase: GalleryUseCase
) : ViewModel() {

    val oAuthField: ObservableField<OAuth> = ObservableField()

    fun getOauthFromStore() {
        compositeDisposable.add(
            mainUseCase.oAuthFromStore()
                .subscribe { oAuthField.set(it)}
        )
    }

    fun getGalleries() {
        galleryUseCase.getGalleries()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        mainUseCase.dispose()
    }
}
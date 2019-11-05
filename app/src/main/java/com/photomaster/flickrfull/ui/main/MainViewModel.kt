package com.photomaster.flickrfull.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.googlecode.flickrjandroid.oauth.OAuth
import com.photomaster.flickrfull.data.remote.entity.HeadOfResponse
import com.photomaster.flickrfull.domain.main.GalleryUseCase
import com.photomaster.flickrfull.domain.main.MainUseCase
import com.photomaster.flickrfull.utils.USER_ID_KEY
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
                .subscribe {
                    oAuthField.set(it)
                }
        )
    }

    fun getGalleries(): LiveData<HeadOfResponse> = LiveDataReactiveStreams.fromPublisher(
        galleryUseCase.getGalleries(
            mainUseCase.getFromStore(
                USER_ID_KEY
            )
        )
    )

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        mainUseCase.dispose()
    }
}
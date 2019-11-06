package com.photomaster.flickrfull.ui.main

import com.photomaster.flickrfull.di.main.PerMain
import javax.inject.Inject

@PerMain
class MainClickHandlers @Inject constructor() {
    fun createClicked(mainViewModel: MainViewModel) {
        mainViewModel.createGallery()
    }
}
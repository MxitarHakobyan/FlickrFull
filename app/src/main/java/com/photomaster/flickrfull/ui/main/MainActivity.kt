package com.photomaster.flickrfull.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.photomaster.flickrfull.R
import com.photomaster.flickrfull.data.remote.entity.HeadOfResponse
import com.photomaster.flickrfull.databinding.ActivityMainBinding
import com.photomaster.flickrfull.ui.common.viewmodels_factory.ViewModelsProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelsProviderFactory

    @Inject
    lateinit var mainClickHandlers: MainClickHandlers

    private lateinit var mainViewModel: MainViewModel
    private lateinit var bsbCreating: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(viewModelStore, factory).get(MainViewModel::class.java)
        binding.viewModel = mainViewModel
        binding.clickHandler = mainClickHandlers
        initView()
        mainViewModel.getOauthFromStore()
        mainViewModel.getGalleries().observe(this, Observer<HeadOfResponse> {
            Log.d("data flow ready", "$it")
        })
    }

    private fun initView() {
        bsbCreating = BottomSheetBehavior.from(bottomSheetCreatingGallery)
        fabAdd.setOnClickListener {
            bsbCreating.state = BottomSheetBehavior.STATE_EXPANDED
        }
        btnCreate.setOnClickListener {
            bsbCreating.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun onBackPressed() {}
}

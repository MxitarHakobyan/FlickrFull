package com.photomaster.flickrfull.ui.main

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.photomaster.flickrfull.R
import com.photomaster.flickrfull.data.locale.shared_prefs.LocalStorageImpl
import com.photomaster.flickrfull.databinding.ActivityMainBinding
import com.photomaster.flickrfull.ui.common.viewmodels_factory.ViewModelsProviderFactory
import com.photomaster.flickrfull.utils.OAUTH_KEY
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    private val TAG = "MainActivity"

    @Inject
    lateinit var factory: ViewModelsProviderFactory

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(viewModelStore, factory).get(MainViewModel::class.java)
        binding.viewModel = mainViewModel
        mainViewModel.getOauthFromStore()
        mainViewModel.getGalleries()
    }
}

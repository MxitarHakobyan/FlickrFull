package com.photomaster.flickrfull.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.photomaster.flickrfull.R
import com.photomaster.flickrfull.databinding.ActivityLoginBinding
import com.photomaster.flickrfull.ui.common.viewmodels_factory.ViewModelsProviderFactory
import com.photomaster.flickrfull.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelsProviderFactory: ViewModelsProviderFactory
    @Inject
    lateinit var loginClickHandler: LoginClickHandler

    private lateinit var loginViewModel: LoginViewModel

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        loginViewModel = ViewModelProvider(
            viewModelStore,
            viewModelsProviderFactory
        ).get(LoginViewModel::class.java)

        loginViewModel.getOauthFromStore()
        loginViewModel.isLoggedIn.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
            }
        })

        binding.viewModel = loginViewModel
        binding.loginClickHandler = loginClickHandler


        loginViewModel.loginUrl.observe(this, Observer {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.toURI().toString())))
        })

    }

    override fun onResume() {
        super.onResume()
        val uri: Uri? = intent.data
        val oauthToken: String
        val oauthVerifier: String
        if (uri != null) {
            oauthToken = uri.getQueryParameter("oauth_token")!!
            oauthVerifier = uri.getQueryParameter("oauth_verifier")!!
            loginViewModel.getAccessToken(oauthToken, oauthVerifier)
        }
    }
}

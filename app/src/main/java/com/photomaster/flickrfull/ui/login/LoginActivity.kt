package com.photomaster.flickrfull.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.photomaster.flickrfull.R
import com.photomaster.flickrfull.domain.LoginUseCase
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class LoginActivity : DaggerAppCompatActivity() {

    private val TAG = "LoginActivity"
    @Inject
    lateinit var loginUseCase: LoginUseCase

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginUseCase.authorize()
        loginUseCase.getUrlSubject()
            .subscribe {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.toURI().toString())))
            }

    }

    override fun onResume() {
        super.onResume()
        val uri: Uri? = intent.data
        uri.let {
            Log.d(TAG, "$it")
        }
    }
}

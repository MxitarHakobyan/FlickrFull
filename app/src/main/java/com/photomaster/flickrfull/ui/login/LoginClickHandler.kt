package com.photomaster.flickrfull.ui.login

import com.photomaster.flickrfull.di.login.PerLogin
import javax.inject.Inject

@PerLogin
class LoginClickHandler @Inject constructor() {

    fun loginClicked(loginViewModel: LoginViewModel) {
        loginViewModel.authorizeUser()
    }
}
package com.photomaster.flickrfull.ui.login

import androidx.lifecycle.ViewModel
import com.photomaster.flickrfull.domain.LoginUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

}
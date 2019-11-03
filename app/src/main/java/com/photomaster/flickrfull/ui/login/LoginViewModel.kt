package com.photomaster.flickrfull.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.googlecode.flickrjandroid.oauth.OAuthToken
import com.photomaster.flickrfull.domain.LoginUseCase
import io.reactivex.disposables.CompositeDisposable
import java.net.URL
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val TAG = "LoginViewModel"

    private val oAuthToken: MutableLiveData<OAuthToken> = MutableLiveData()
    val loginUrl: MutableLiveData<URL> = MutableLiveData()

    fun authorizeUser() {
        loginUseCase.authorize()

        compositeDisposable.add(
            loginUseCase.getOAuthTokenObservable()
                .subscribe { oAuthToken.value = it }
        )

        compositeDisposable.add(
            loginUseCase.getUrlObservable()
                .subscribe { loginUrl.postValue(it) }
        )
    }

    fun getAccessToken(
        token: String,
        oauthVerifier: String
    ) {
        loginUseCase.getAccessToken(token, oAuthToken.value!!.oauthTokenSecret ,oauthVerifier);
        compositeDisposable.add(loginUseCase.getOAuthObservable()
            .subscribe { t -> Log.d(TAG, t.token.toString()) }
        )
    }

    override fun onCleared() {
        super.onCleared()
        loginUseCase.dispose()
        compositeDisposable.dispose()
    }
}
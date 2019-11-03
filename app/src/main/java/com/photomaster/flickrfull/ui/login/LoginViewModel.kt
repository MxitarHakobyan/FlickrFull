package com.photomaster.flickrfull.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.photomaster.flickrfull.domain.login.LoginUseCase
import io.reactivex.disposables.CompositeDisposable
import java.net.URL
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val loginUrl: MutableLiveData<URL> = MutableLiveData()
    val isLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    fun authorizeUser() {
        loginUseCase.authorize()

        compositeDisposable.add(
            loginUseCase.getOAuthTokenObservable()
                .subscribe { loginUseCase.storeOauthSecret(it.oauthTokenSecret) }
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
        loginUseCase.getAccessToken(
            token,
            loginUseCase.getOAuthTokenSecret(),
            oauthVerifier
        );
        compositeDisposable.add(loginUseCase.getOAuthObservable()
            .subscribe {
                compositeDisposable.add(
                    loginUseCase.storeOAuth(it)
                        .subscribe { b ->
                            isLoggedIn.postValue(b)
                        }
                )
            }
        )
    }

    fun getOauthFromStore() {
        compositeDisposable.add(
            loginUseCase.oAuthFromStore()
                .subscribe { isLoggedIn.postValue(it) }
        )
    }

    override fun onCleared() {
        super.onCleared()
        loginUseCase.dispose()
        compositeDisposable.dispose()
    }
}
package com.photomaster.flickrfull.oauth

import com.googlecode.flickrjandroid.Flickr
import com.googlecode.flickrjandroid.auth.Permission
import com.googlecode.flickrjandroid.oauth.OAuth
import com.googlecode.flickrjandroid.oauth.OAuthToken
import com.photomaster.flickrfull.di.login.PerLogin
import com.photomaster.flickrfull.utils.CALLBACK_URL
import java.net.URL
import javax.inject.Inject

@PerLogin
class FlickrClient @Inject constructor(private val flickr: Flickr) {

    fun getOAuthInterface(): OAuthToken {
        return flickr.oAuthInterface.getRequestToken(CALLBACK_URL)
    }

    fun getAuthorizationResponse(oAuthToken: OAuthToken): URL {
        return flickr.oAuthInterface.buildAuthenticationUrl(Permission.DELETE, oAuthToken)
    }

    fun getAccessToken(
        token: String,
        tokenSecret: String,
        oauthVerifier: String
    ): OAuth {
        return flickr.oAuthInterface.getAccessToken(
            token,
            tokenSecret,
            oauthVerifier
        )
    }
}
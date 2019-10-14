package com.photomaster.flickrfull.data.remote

import retrofit2.http.GET

interface FlickrApi {
    @GET("oauth/request_token" +
            "?oauth_nonce=89601180" +
            "&oauth_timestamp=1305583298" +
            "&oauth_consumer_key=653e7a6ecc1d528c516cc8f92cf98611" +
            "&oauth_signature_method=HMAC-SHA1" +
            "&oauth_version=1.0" +
            "&oauth_callback=http%3A%2F%2Fwww.example.com")
    fun getRequestToken()

}
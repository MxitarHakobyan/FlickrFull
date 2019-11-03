package com.photomaster.flickrfull.data.local.shared_prefs

import com.google.gson.Gson
import com.googlecode.flickrjandroid.oauth.OAuth

object OAuthConverter {

    fun toJson(oAuth: OAuth): String = Gson().toJson(oAuth)

    fun fromJson(json: String): OAuth = Gson().fromJson(json, OAuth::class.java)
}


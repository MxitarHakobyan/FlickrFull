package com.photomaster.flickrfull.data.local.shared_prefs

import io.reactivex.Observable

interface LocalStorage {
    fun writeTo(
        key: String,
        value: String
    )

    fun readFrom(key: String): String
}
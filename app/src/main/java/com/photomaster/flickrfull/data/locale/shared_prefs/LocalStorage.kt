package com.photomaster.flickrfull.data.locale.shared_prefs

interface LocalStorage {
    fun writeTo(
        key: String,
        value: String
    )

    fun readFrom(key: String): String
}
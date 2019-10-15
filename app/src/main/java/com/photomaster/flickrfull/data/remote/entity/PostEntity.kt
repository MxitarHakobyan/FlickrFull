package com.photomaster.flickrfull.data.remote.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostEntity (

    @Expose
    @SerializedName("userId")
    var userId: Int,

    @Expose
    @SerializedName("id")
    var id: Int,

    @Expose
    @SerializedName("title")
    var title: String,

    @Expose
    @SerializedName("body")
    var body: String
)
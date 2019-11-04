package com.photomaster.flickrfull.data.remote.entity

import com.google.gson.annotations.SerializedName

data class Galleries(

    @SerializedName("total")
    val total: Float,

    @SerializedName("per_page")
    val perPage: Float,

    @SerializedName("user_id")
    val userId: String,

    @SerializedName("page")
    val page: Float,

    @SerializedName("pages")
    val pages: Float,

    @SerializedName("gallery")
    val gallery: List<Gallery>

)
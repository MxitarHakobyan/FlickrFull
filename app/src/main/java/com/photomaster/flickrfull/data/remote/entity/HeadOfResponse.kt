package com.photomaster.flickrfull.data.remote.entity

import com.google.gson.annotations.SerializedName

data class HeadOfResponse(

    @SerializedName("galleries")
    val galleries: Galleries,

    @SerializedName("stat")
    val stat: String

)

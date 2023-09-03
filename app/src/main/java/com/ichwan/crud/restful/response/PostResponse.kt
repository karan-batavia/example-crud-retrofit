package com.ichwan.crud.restful.response

import com.google.gson.annotations.SerializedName

/**
 * serialize name digunakan untuk meng-custom nama variable
 */
data class PostResponse(
    val id: Int? = 0,
    @SerializedName("title")
    val text: String? = null,
    @SerializedName("body")
    val content: String? = null
)

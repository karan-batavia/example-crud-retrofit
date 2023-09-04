package com.ichwan.crud.restful.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * serialize name digunakan untuk meng-custom nama variable
 */
@Parcelize
data class PostResponse(
    val id: Int? = 0,
    @SerializedName("title")
    val text: String? = null,
    @SerializedName("body")
    val content: String? = null
) : Parcelable

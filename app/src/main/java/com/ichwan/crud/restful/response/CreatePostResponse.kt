package com.ichwan.crud.restful.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreatePostResponse (
    val id: Int? = 0,

    val userId: String? = null,

    @SerializedName("title")
    val text: String? = null,

    @SerializedName("body")
    val content: String? = null
) : Parcelable
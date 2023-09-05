package com.ichwan.crud.restful.response

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    val postId: Int,

    val id: Int,

    @SerializedName("name")
    val nameComments: String,

    @SerializedName("email")
    val mailComments: String,

    @SerializedName("body")
    val comments: String
)

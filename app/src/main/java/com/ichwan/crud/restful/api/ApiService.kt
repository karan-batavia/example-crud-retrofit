package com.ichwan.crud.restful.api

import com.ichwan.crud.restful.response.CreatePostResponse
import com.ichwan.crud.restful.response.PostResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("posts")
    fun getPosts(): Call<List<PostResponse>>

    @FormUrlEncoded
    @POST("posts")
    fun createPosts(
        @Field("userId") userId: String,
        @Field("title") text: String,
        @Field("body") content: String
    ): Call<List<CreatePostResponse>>
}
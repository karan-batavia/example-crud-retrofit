package com.ichwan.crud.restful.api

import com.ichwan.crud.restful.response.CommentResponse
import com.ichwan.crud.restful.response.PostResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.lang.reflect.Parameter

interface ApiService {

    @GET("posts")
    fun getPosts(): Call<List<PostResponse>>

    @GET("posts/{postId}/comments")
    fun getPostsById(@Path("postId") postId: Int): Call<List<CommentResponse>>

    @GET("posts")
    fun getPostsByUserId(@Query("userId") userId: String): Call<PostResponse>

    @GET("posts")
    fun getPostsByQueryMap(@QueryMap parameter: HashMap<String, String>): Call<PostResponse>

    @FormUrlEncoded
    @POST("posts")
    fun createPosts(
        @Field("userId") userId: String,
        @Field("title") text: String,
        @Field("body") content: String
    ): Call<PostResponse>

    //if you used put, all required data will be updated
    @FormUrlEncoded
    @PUT("posts/{postId}")
    fun putPosts(
        @Path("postId") idPost: Int,
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String?,
        @Field("body") body: String?
    ): Call<PostResponse>

    //if you used patch, only several data has depending on the user
    @FormUrlEncoded
    @PATCH("posts/{postId}")
    fun patchPosts(
        @Path("postId") idPost: Int,
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String?,
        @Field("body") body: String?
    ): Call<PostResponse>

    @DELETE("posts/{postId}")
    fun deletePosts(@Path("postId") id: Int): Call<Void>
}
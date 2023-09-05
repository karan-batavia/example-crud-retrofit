# Example Project CRUD with Retrofit


## Purpose

example of a simple CRUD project using the Retrofit library to manage APIs from a dummy API provider website [JSON Placeholder](jsonplaceholder.typicode.com/)

## Getting Started

### Prerequisites

Make sure your Android device has API level 24 or higher.

### Clone Project

To get started with the project, clone it using the following command:

```
git clone "https://github.com/ichwansh03/ExampleCRUDRetrofit.git"
```

## Libraries Used

The following libraries are used in the project:
* [AndroidX Core-KTX](https://developer.android.com/kotlin/ktx) - AndroidX Core library with Kotlin extensions for writing concise, idiomatic Kotlin code.
* [AndroidX AppCompat](https://developer.android.com/jetpack/androidx/releases/appcompat) - AndroidX AppCompat library for providing backward compatibility for newer Android features on older devices.
* [Google Material Components](https://material.io/develop/android) - Material Design components by Google for creating a visually appealing and consistent user interface.
* [ConstraintLayout](https://developer.android.com/training/constraint-layout) - Android's ConstraintLayout for creating flexible and responsive layouts.
* [Retrofit](https://square.github.io/retrofit/) - Retrofit library for handling network requests and responses with a type-safe approach.
* [OkHttp Logging Interceptor](https://square.github.io/okhttp/interceptors) - OkHttp Logging Interceptor for logging HTTP interactions.
* [GSON Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) - Retrofit GSON Converter for parsing JSON responses using GSON.

## Project Documentations

### Request Method

In this project, the request method used are `GET`, `POST`, `PUT`, `PATCH` and `DELETE`.

* Get Data

API service endpoint:
```kotlin
    @GET("posts")
    fun getPosts(): Call<List<PostResponse>>

    @GET("posts/{postId}/comments")
    fun getPostsById(@Path("postId") postId: Int): Call<List<CommentResponse>>

    @GET("posts")
    fun getPostsByUserId(@Query("userId") userId: String): Call<PostResponse>

    @GET("posts")
    fun getPostsByQueryMap(@QueryMap parameter: HashMap<String, String>): Call<PostResponse>
```

Implemented method:

```kotlin
    private fun fetchDataFromApiByMap() {
        val apiService = ApiClient.getApiService()

        val params = HashMap<String, String>()
        params["userId"] = "1"
        params["postId"] = "2"
        val call = apiService.getPostsByQueryMap(params)
    }

    private fun fetchDataFromApiById() {
        val apiService = ApiClient.getApiService()
        val call = apiService.getPostsByUserId("1")
    }

    private fun fetchDataFromApi() {
        val apiService = ApiClient.getApiService()
        val call = apiService.getPosts()

        call.enqueue(object : retrofit2.Callback<List<PostResponse>> {
            override fun onResponse(
                call: Call<List<PostResponse>>,
                response: Response<List<PostResponse>>
            ) {
                if (response.isSuccessful) {
                    val posts = response.body()

                    if (posts != null){
                        list.clear()
                        list.addAll(posts)
                        adapter?.notifyDataSetChanged()
                    }
                } else {
                    Log.e("Error Call Request ", response.message())
                }
            }

            override fun onFailure(call: Call<List<PostResponse>>, t: Throwable) {
                Log.e("onFailure ", t.message.toString())
            }

        })
    }
```

* Create Data

Api service endpoint:

```kotlin
    @FormUrlEncoded
    @POST("posts")
    fun createPosts(
        @Field("userId") userId: String,
        @Field("title") text: String,
        @Field("body") content: String
    ): Call<PostResponse>
```

Implemented method:

```kotlin
        val apiService = ApiClient.getApiService()

        binding.apply {

            apiService.createPosts(
                useridInput.text.toString(),
                titleInput.text.toString(),
                bodyInput.text.toString()
            ).enqueue(object : retrofit2.Callback<PostResponse> {
                override fun onResponse(
                    call: Call<PostResponse>,
                    response: Response<PostResponse>
                ) {

                }

                override fun onFailure(call: Call<PostResponse>, t: Throwable) {

                }

            })
        }
```

* Update Data

Api service endpoint:

```kotlin
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

```

Implemented method:

```kotlin
  val apiService = ApiClient.getApiService()
  apiService.patchPosts(1, 2, 1, null, "Hello world")
```

* Delete Data

Api service endpoint:

```kotlin
    @DELETE("posts/{postId}")
    fun deletePosts(@Path("postId") id: Int): Call<Void>
```

Implemented method

```kotlin
  val apiService = ApiClient.getApiService()
  apiService.deletePosts(1)
```

### 


---
Feel free to reach out if you have any questions or need further assistance with the project! Happy coding! 🚀

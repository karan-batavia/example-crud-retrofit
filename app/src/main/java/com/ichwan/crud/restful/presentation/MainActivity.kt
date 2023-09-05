package com.ichwan.crud.restful.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.crud.restful.adapter.PostsAdapter
import com.ichwan.crud.restful.api.ApiClient
import com.ichwan.crud.restful.databinding.ActivityMainBinding
import com.ichwan.crud.restful.listener.OnPostsClickListener
import com.ichwan.crud.restful.response.PostResponse
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var adapter: PostsAdapter? = null
    private var manager: RecyclerView.LayoutManager? = null
    private var list = ArrayList<PostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            buttonComments.setOnClickListener {
                startActivity(Intent(this@MainActivity, CommentActivity::class.java))
            }

            buttonAddPost.setOnClickListener {
                startActivity(Intent(this@MainActivity, ManagePostsActivity::class.java))
            }
        }

        setupRecyclerView(this)
        //fetchDataFromApi()
        //fetchDataFromApiById()
        fetchDataFromApiByMap()
    }

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

    private fun setupRecyclerView(context: Context) {
        adapter = PostsAdapter(
            list,
            (object : OnPostsClickListener {
                override fun onPostClick(posts: PostResponse) {
                    val intent = Intent(this@MainActivity, ManagePostsActivity::class.java)
                    intent.putExtra(ManagePostsActivity.posts, posts)
                    startActivity(intent)
                }

            }))
        manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.apply {
            rvPosts.adapter = adapter
            rvPosts.layoutManager = manager
        }
    }
}
package com.ichwan.crud.restful.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.crud.restful.adapter.CommentAdapter
import com.ichwan.crud.restful.api.ApiClient
import com.ichwan.crud.restful.databinding.ActivityMainBinding
import com.ichwan.crud.restful.response.CommentResponse
import retrofit2.Call
import retrofit2.Response

class CommentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var adapter: CommentAdapter? = null
    private var manager: RecyclerView.LayoutManager? = null
    private var list = ArrayList<CommentResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView(this)
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        val apiService = ApiClient.getApiService()
        val call = apiService.getPostsById(1)

        call.enqueue(object : retrofit2.Callback<List<CommentResponse>> {
            override fun onResponse(
                call: Call<List<CommentResponse>>,
                response: Response<List<CommentResponse>>
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

            override fun onFailure(call: Call<List<CommentResponse>>, t: Throwable) {
                Log.e("onFailure ", t.message.toString())
            }

        })
    }

    private fun setupRecyclerView(context: Context) {
        adapter = CommentAdapter(list)
        manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.apply {
            rvPosts.adapter = adapter
            rvPosts.layoutManager = manager
        }
    }
}
package com.ichwan.crud.restful.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.crud.restful.R
import com.ichwan.crud.restful.adapter.PostsAdapter
import com.ichwan.crud.restful.api.ApiClient
import com.ichwan.crud.restful.databinding.FragmentListPostsBinding
import com.ichwan.crud.restful.response.PostResponse
import retrofit2.Call
import retrofit2.Response

class ListPostsFragment : Fragment() {

    private lateinit var binding: FragmentListPostsBinding

    private var adapter: PostsAdapter? = null
    private var manager: RecyclerView.LayoutManager? = null
    private var list = ArrayList<PostResponse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListPostsBinding.inflate(layoutInflater, container, false)

        setupRecyclerView(requireContext())
        fetchDataFromApi()

        return binding.root
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
        adapter = PostsAdapter(list)
        manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.apply {
            rvPosts.adapter = adapter
            rvPosts.layoutManager = manager
        }
    }
}
package com.ichwan.crud.restful.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ichwan.crud.restful.R
import com.ichwan.crud.restful.api.ApiClient
import com.ichwan.crud.restful.databinding.ActivityManagePostsBinding
import com.ichwan.crud.restful.response.PostResponse
import retrofit2.Call
import retrofit2.Response
import java.util.Date

class ManagePostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManagePostsBinding

    companion object {
        const val posts = "posts"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagePostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonSave.setOnClickListener {
                createDataFromApi()
            }
        }
    }

    private fun createDataFromApi() {
        val apiService = ApiClient.getApiService()

        binding.apply {

            apiService.createPosts(
                useridInput.text.toString(),
                titleInput.text.toString(),
                bodyInput.text.toString()
            ).enqueue( object : retrofit2.Callback<List<PostResponse>> {
                override fun onResponse(
                    call: Call<List<PostResponse>>,
                    response: Response<List<PostResponse>>
                ) {
                    if (response.isSuccessful){

                        //use view model for updated data from UI
                        txUserid.text = useridInput.text.toString()
                        txTitle.text = titleInput.text.toString()
                        txBody.text = bodyInput.text.toString()
                        txCreated.text = Date().toString()
                        txUpdated.text = null

                        Toast.makeText(this@ManagePostsActivity, response.message(), Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e("Error Create Request ", response.message())
                    }
                }

                override fun onFailure(call: Call<List<PostResponse>>, t: Throwable) {
                    Log.d("Error CreateDataFromApi() ", t.message.toString())
                }

            })
        }
    }
}
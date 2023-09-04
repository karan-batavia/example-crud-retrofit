package com.ichwan.crud.restful.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ichwan.crud.restful.R
import com.ichwan.crud.restful.databinding.ActivityManagePostsBinding

class ManagePostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManagePostsBinding

    companion object {
        const val posts = "posts"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagePostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
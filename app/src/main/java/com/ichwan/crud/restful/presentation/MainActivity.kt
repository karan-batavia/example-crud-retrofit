package com.ichwan.crud.restful.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ichwan.crud.restful.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
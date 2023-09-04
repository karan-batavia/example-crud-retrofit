package com.ichwan.crud.restful.presentation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.ichwan.crud.restful.R
import com.ichwan.crud.restful.databinding.FragmentManagePostsBinding
import com.ichwan.crud.restful.response.PostResponse

class ManagePostsFragment : Fragment() {

    companion object {
        const val posts = "posts"
    }

    private lateinit var binding: FragmentManagePostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentManagePostsBinding.inflate(layoutInflater, container, false)

        val receivedPosts = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(posts, PostResponse::class.java)
        } else {
            arguments?.getParcelable(posts)
        }

        binding.apply {

            receivedPosts?.let { postResponse ->
                titleInput.setText(postResponse.text ?: "")
                bodyInput.setText(postResponse.text ?: "")
            }

            buttonSave.setOnClickListener {
                savePost()
            }
            buttonUpdate.setOnClickListener {
                updateData()
            }
            buttonDelete.setOnClickListener {
                deleteData()
            }
        }

        return binding.root
    }

    private fun deleteData() {

    }

    private fun updateData() {

    }

    private fun savePost() {

    }

}
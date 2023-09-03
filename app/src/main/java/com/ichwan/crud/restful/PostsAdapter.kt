package com.ichwan.crud.restful

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.crud.restful.databinding.ItemPostsBinding
import com.ichwan.crud.restful.response.PostResponse

class PostsAdapter(val list: ArrayList<PostResponse>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemPostsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.apply {
                    title.text = text
                    body.text = content
                }
            }
        }
    }
}
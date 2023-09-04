package com.ichwan.crud.restful.listener

import com.ichwan.crud.restful.response.PostResponse

interface OnPostsClickListener {
    fun onPostClick(posts: PostResponse)
}
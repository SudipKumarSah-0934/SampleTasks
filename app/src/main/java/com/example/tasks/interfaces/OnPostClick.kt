package com.example.tasks.interfaces

import com.example.tasks.models.PostsModel

interface OnPostClick {
    fun onPostClick(postDetails: PostsModel)
}
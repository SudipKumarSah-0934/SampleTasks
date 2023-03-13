package com.example.tasks.api

import com.example.tasks.models.PostsModel
import com.example.tasks.models.PostsTable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsService {

    @GET("posts")
    suspend fun getPosts() : List<PostsModel>

}
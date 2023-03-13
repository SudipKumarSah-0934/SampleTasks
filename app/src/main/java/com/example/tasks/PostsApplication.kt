package com.example.tasks

import android.app.Application
import com.example.tasks.api.PostsService
import com.example.tasks.api.RetrofitHelper
import com.example.tasks.db.PostsDatabase
import com.example.tasks.repository.PostsRepository

class PostsApplication : Application() {

    lateinit var postsRepository: PostsRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val postsService = RetrofitHelper.getInstance().create(PostsService::class.java)
        val database = PostsDatabase.getDatabase(applicationContext)
        postsRepository = PostsRepository(postsService, database, applicationContext)
    }
}
package com.example.tasks.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.api.PostsService
import com.example.tasks.db.PostsDatabase
import com.example.tasks.models.PostsTable
import com.example.tasks.utils.CheckInternetConnection
import com.example.tasks.views.HomeFragment

class PostsRepository(
    private val postsService: PostsService,
    private val postsDatabase: PostsDatabase,
    private val applicationContext: Context
) {
    private  val TAG = "PostsRepository"
    private val postsLiveData = MutableLiveData<PostsTable>()

    val postList: LiveData<PostsTable>
    get() = postsLiveData

    suspend fun getPost(){
        if(CheckInternetConnection.isInternetAvailable(applicationContext)){
            val result = postsService.getPosts()
            if(result != null){
                postsDatabase.postsDao().addPosts(result)
                postsLiveData.postValue(PostsTable(result))
            }
        }
        else{
            val posts= postsDatabase.postsDao().getPosts()
            val postsList = PostsTable(posts)
            postsLiveData.postValue(postsList)
        }
    }
}








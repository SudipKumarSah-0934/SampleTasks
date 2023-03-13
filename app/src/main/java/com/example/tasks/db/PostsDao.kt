package com.example.tasks.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tasks.models.PostsModel


@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPosts(posts: List<PostsModel>)

    @Query("SELECT * FROM posts")
    suspend fun getPosts() : List<PostsModel>
}
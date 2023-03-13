package com.example.tasks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.models.PostsTable
import com.example.tasks.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PostsRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getPost()
        }
    }

    val postList : LiveData<PostsTable>
    get() = repository.postList

}
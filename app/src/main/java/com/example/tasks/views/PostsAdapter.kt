package com.example.tasks.views

import android.content.Context
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.interfaces.OnPostClick
import com.example.tasks.models.PostsModel


class PostsAdapter(
    private val context: Context, private val postsList: List<PostsModel>, private val onPostClick: OnPostClick,
) : RecyclerView.Adapter<PostsAdapter.PostsAdapterVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapterVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.details_posts, parent, false)
        return PostsAdapterVH(view)
    }
    override fun onBindViewHolder(holder: PostsAdapterVH, position: Int) {
        val recentPostList : PostsModel = postsList[position]
        holder.titleTxt.text = recentPostList.title
        holder.itemView.setOnClickListener {
            onPostClick.onPostClick(recentPostList)
        }
    }
    override fun getItemCount(): Int {
        return postsList.size
    }
    inner class PostsAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTxt: TextView = itemView.findViewById(R.id.tv_title)
    }
}
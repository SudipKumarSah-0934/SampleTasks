package com.example.tasks.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.databinding.FragmentHomeBinding
import com.example.tasks.interfaces.OnPostClick
import com.example.tasks.models.PostsModel

class HomeFragment : Fragment(),OnPostClick {
    private lateinit var binding: FragmentHomeBinding
    lateinit var postsList: List<PostsModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home, container, false
        )
        getPosts()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(postsList: List<PostsModel>) =
            HomeFragment().apply {
                this.postsList=postsList
            }
    }

    private fun getPosts() {
        val recyclerView: RecyclerView = binding.PostsList
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        val adapter = PostsAdapter(requireContext(), postsList,this@HomeFragment)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onPostClick(postDetails: PostsModel) {
        showFragment(PostDetailsFragment.newInstance(postDetails))
    }
    private fun showFragment(fragment: Fragment?) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in,  // enter
            R.anim.fade_out,  // exit
            R.anim.fade_in,  // popEnter
            R.anim.slide_out // popExit
        )
        fragmentTransaction.replace(R.id.fragment_container, fragment!!)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commit()
    }
}
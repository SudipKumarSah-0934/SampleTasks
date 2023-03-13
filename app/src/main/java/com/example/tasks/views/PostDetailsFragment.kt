package com.example.tasks.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.example.tasks.R
import com.example.tasks.databinding.DetailsPostsBinding
import com.example.tasks.databinding.FragmentHomeBinding
import com.example.tasks.databinding.FragmentPostDetailsBinding
import com.example.tasks.models.PostsModel

class PostDetailsFragment : Fragment() {
    lateinit var postDetails: PostsModel
    private lateinit var binding: FragmentPostDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentPostDetailsBinding.inflate(inflater, container, false)
        binding.tvPostHeader.text=postDetails.title
        binding.tvDesc.text=postDetails.body
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(postDetails: PostsModel) =
            PostDetailsFragment().apply {
                this.postDetails=postDetails
            }
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
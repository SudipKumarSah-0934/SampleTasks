package com.example.tasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.api.PostsService
import com.example.tasks.api.RetrofitHelper
import com.example.tasks.databinding.ActivityMainBinding
import com.example.tasks.viewmodels.MainViewModel
import com.example.tasks.viewmodels.MainViewModelFactory
import com.example.tasks.views.HomeFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        val repository = (application as PostsApplication).postsRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.postList.observe(this, Observer {
            showFragment(HomeFragment.newInstance(it.results))
        })
        binding.allowPermissions.setOnClickListener {
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }

    }
    private fun showFragment(fragment: Fragment) {
        val fram = supportFragmentManager.beginTransaction()
        fram.setCustomAnimations(
            R.anim.slide_in,  // enter
            R.anim.fade_out,  // exit
            R.anim.fade_in,  // popEnter
            R.anim.slide_out // popExit
        )
        fram.replace(R.id.fragment_container, fragment)
        fram.commit()
    }
    override fun onBackPressed() {
        loadExitDialog()
    }
    private var doubleBackToExitPressedOnce = false
    private fun loadExitDialog() {
        try {
            if (doubleBackToExitPressedOnce) {
                finishAffinity()
                return
            }
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, getString(R.string.exitDoublepress), Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
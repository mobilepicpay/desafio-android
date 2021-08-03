package com.picpay.android.util

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.picpay.android.util.databinding.ActivityMainBinding

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding  : ActivityMainBinding
    val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        navController.graph = navController.navInflater.inflate(getNavGraph())
    }

    fun loadState(state: Boolean) {
        binding.userListProgressBar.visibility = if (state) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    abstract fun getNavGraph() : Int

}
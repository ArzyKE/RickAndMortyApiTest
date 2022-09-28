package com.example.rickandmortyapitest.presentation.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapitest.R
import com.example.rickandmortyapitest.databinding.ActivityMain2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main2) {

    private lateinit var navController: NavController
    private val binding by viewBinding(ActivityMain2Binding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
        setupListeners()
    }

    private fun setupListeners() {
        onDestinationChangedListener()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.charactersFragment,
            R.id.locationsFragment,
            R.id.episodesFragment,
            R.id.searchFragment
        ).build()

        setupWithNavController(binding.bottomNavigation, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    private fun onDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.charactersFragment -> {
                }
                R.id.locationsFragment -> {
                }
                R.id.episodesFragment -> {
                }
                R.id.searchFragment -> {
                }
                else -> {
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
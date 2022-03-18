package com.app.ecommerceapp.features.home.presentation.screens

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.app.ecommerceapp.R
import com.app.ecommerceapp.core.common.Constants.COLOR_DARK_BLUE
import com.app.ecommerceapp.core.common.Constants.COLOR_ORANGE
import com.app.ecommerceapp.core.common.Constants.COLOR_WHITE_BACKGROUND
import com.app.ecommerceapp.core.common.changeNavigationBarColor
import com.app.ecommerceapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment

        navController = navHostFragment.navController

        setUpNavigationListener()
        setUpBottomNavMenu()
        disableBottomViewClick()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter -> navController.navigate(R.id.action_mainFragment_to_filterFragment)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setUpBottomNavMenu() {
        binding.bottomNavigationView.apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.cart -> {
                        navController.navigate(R.id.action_mainFragment_to_cartFragment)
                    }
                }
                true
            }
            getOrCreateBadge(R.id.cart).apply {
                number = BADGE_COUNT
                backgroundColor = ContextCompat.getColor(context, COLOR_ORANGE)
            }
        }
    }

    private fun disableBottomViewClick() {
        binding.bottomNavBackground.setOnClickListener(null)
    }

    private fun setUpNavigationListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.label == "HomeFragment") {
                changeBottomNavVisibility(isVisible = true)
                changeNavigationBarColor(color = COLOR_DARK_BLUE)
            } else {
                changeBottomNavVisibility(isVisible = false)
                changeNavigationBarColor(color = COLOR_WHITE_BACKGROUND)
            }
        }
    }

    private fun changeBottomNavVisibility(isVisible: Boolean) {
        binding.apply {
            bottomNavBackground.isVisible = isVisible
            bottomNavigationView.isVisible = isVisible
            ivCircle.isVisible = isVisible
            tvExplorer.isVisible = isVisible
        }
    }

    private companion object {
        private const val BADGE_COUNT = 4
    }
}
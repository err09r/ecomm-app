package com.app.ecommerceapp.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.app.ecommerceapp.util.constants.ResourceConstants.COLOR_DARK_BLUE
import com.app.ecommerceapp.util.constants.ResourceConstants.COLOR_ORANGE
import com.app.ecommerceapp.util.constants.ResourceConstants.COLOR_WHITE_BACKGROUND
import com.app.ecommerceapp.databinding.ActivityMainBinding
import com.app.ecommerceapp.util.extensions.changeNavigationBarColor
import com.app.ecommerceapp.firebase.DESTINATION_KEY
import dagger.hilt.android.AndroidEntryPoint
import com.app.feature_home.R as CoreR
import com.app.navigation.R as NavR

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

        intent.extras?.let { bundle ->
            when (bundle.getString(DESTINATION_KEY)) {
                DESTINATION_CART -> navController.navigate(NavR.id.action_homeFragment_to_cartFragment)
                DESTINATION_DETAIL -> navController.navigate(NavR.id.action_homeFragment_to_detailFragment)
                DESTINATION_MAP -> navController.navigate(NavR.id.action_homeFragment_to_mapFragment)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            CoreR.id.filter -> navController.navigate(NavR.id.action_homeFragment_to_filterFragment)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(CoreR.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setUpBottomNavMenu() {
        binding.bottomNavigationView.apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    CoreR.id.cart -> {
                        navController.navigate(NavR.id.action_homeFragment_to_cartFragment)
                    }
                }
                true
            }
            getOrCreateBadge(CoreR.id.cart).apply {
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
            if (destination.label == HOME_FRAGMENT_LABEL) {
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
        private const val BADGE_COUNT = 3
        private const val HOME_FRAGMENT_LABEL = "HomeFragment"
        private const val DESTINATION_CART = "cart"
        private const val DESTINATION_DETAIL = "detail"
        private const val DESTINATION_MAP = "map"
    }
}
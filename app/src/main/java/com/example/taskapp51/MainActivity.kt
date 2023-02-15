package com.example.taskapp51

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskapp51.databinding.ActivityMainBinding
import com.example.taskapp51.utils.Preferences
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.navigate(R.id.authFragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.newTaskFragment
            )
        )
        if (!Preferences(applicationContext).isBoardingShowed()) {
            navController.navigate(R.id.onBoardFragment)
        } else if (auth.currentUser == null) {
            navController.navigate(R.id.authFragment)
        }

        val listWithoutBottomNav =
            setOf(R.id.newTaskFragment, R.id.onBoardFragment, R.id.authFragment)
        val listWithoutAppBar = setOf(R.id.onBoardFragment, R.id.authFragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (listWithoutBottomNav.contains(destination.id)) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
            if (listWithoutAppBar.contains(destination.id)) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
package com.senemyalin.sencoffee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.senemyalin.sencoffee.common.viewBinding
import com.senemyalin.sencoffee.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        with(binding) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController: NavController = navHostFragment.navController

            NavigationUI.setupWithNavController(bottomNavigationView, navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.loginFragment -> {
                        binding.bottomNavigationView.visibility = View.GONE
                    }

                    R.id.registerFragment -> {
                        binding.bottomNavigationView.visibility = View.GONE
                    }

                    else -> {
                        binding.bottomNavigationView.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
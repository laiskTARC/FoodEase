package com.example.foodease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.foodease.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    private lateinit var navController:  NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        val toolbar = binding.toolbar2
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as
                NavHostFragment
        navController = navHostFragment.navController

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)


        binding.bottomNavAdmin.setOnItemSelectedListener { item ->
            when (item.itemId) {
//                R.id.itemEvent -> {
//                    // Navigate to the "Event" fragment destination
//                    navController.navigate(R.id.eventFragment)
//                    true
//                }

                R.id.itemInventory -> {
                    // Navigate to the 'Inventory" fragment destination
                    navController.navigate(R.id.inventoryListFragment)
                    true
                }
                // Handle other bottom navigation items similarly
                else -> false
            }
        }

        //val toolbarTitle = toolbar.findViewById<TextView>(R.id.toolbar2)

    }

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.itemEvent -> {
                findNavController(R.id.fragment).navigate(R.id.action_loginFragment_to_eventList)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

     */

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
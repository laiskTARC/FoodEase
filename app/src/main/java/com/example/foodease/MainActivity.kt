package com.example.foodease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.foodease.databinding.ActivityMainBinding
import com.example.foodease.ui.event.EventList
import com.example.foodease.ui.volunteer.VolunteerListFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController:  NavController
    private lateinit var binding: ActivityMainBinding

    private var doubleBackToExit = false

    // Define a set of allowed destinations for non-logged-in users
    private val allowedDestinations = setOf(
        R.id.loginFragment,
        R.id.registerFragment,
        R.id.selectLogin
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val toolbar = binding.toolbar2
        val toolbarTitle = binding.toolbarTitle
        setSupportActionBar(toolbar)
        // Hide default title
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // Hide the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as
                NavHostFragment

        // Initialize navController first
        navController = navHostFragment.navController
// Add a destination change listener to update the title
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Update the title whenever the destination changes
            toolbarTitle.text = destination.label
        }
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // 初始化Firebase Authentication
        val auth = FirebaseAuth.getInstance()

        auth.addAuthStateListener {
            if(it.currentUser != null){
                Log.i("firebase", "Auth Yes")

                navigationForLoggedIn()
            }

            else{
                navController.navigate(R.id.selectLogin)
                Log.i("firebase", "Auth Fusck")

            }

        }

        /*navController.addOnDestinationChangedListener { _, destination, _ ->
            val user = FirebaseAuth.getInstance().currentUser
                if (user == null && destination.id !in allowedDestinations) {
                    Log.i("firebase", "Auth Fuin $user")
                    // User is trying to navigate to a restricted destination
                    // Navigate them back to the login fragment
                    navController.navigate(R.id.selectLogin)
                    // Display a message or perform any other action
                    Toast.makeText(this, "Access Denied", Toast.LENGTH_SHORT).show()
                }

            else if(user != null && destination.id in allowedDestinations){
                    navController.navigate(R.id.homeFragment)
                }

        }*/


    }

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.itemEvent -> {
                findNavController(R.id.fragment).navigate(R.id.eventFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
*/


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


    private fun initializeNavigation(auth: FirebaseAuth) {
        auth.addAuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // User is logged in, allow navigation to all destinations
                navigationForLoggedIn()
                navController.navigate(R.id.homeFragment)
            } else {
                // User is not logged in, set up restricted navigation
                redirectHome()
                navigationForNonLoggedIn()
            }
        }
    }

    private fun navigationForLoggedIn(){

        binding.bottomNavAdmin.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemEvent -> {
                    Log.d("Navigation", "Navigating to EventFragment")
                    // Navigate to the "Event" fragment destination
                    findNavController(R.id.fragment).navigate(R.id.eventFragment)
                    true
                }

                R.id.itemInventory -> {
                    // Navigate to the "Event" fragment destination
                    navController.navigate(R.id.inventoryListFragment)
                    true
                }

                R.id.itemUser -> {
                    // Navigate to the "Event" fragment destination
                    navController.navigate(R.id.volunteerListFragment)
                    true
                }

                R.id.itemProfile->{
                    // Navigate to the "Event" fragment destination
                    navController.navigate(R.id.profileFragment)
                    true
                }
                R.id.itemDashboard -> {
                    // Navigate to the "Event" fragment destination
                    navController.navigate(R.id.homeFragment)
                    true
                }
                // Add more allowed destinations here

                else -> false
            }
        }
    }

    private fun navigationForNonLoggedIn(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id !in allowedDestinations) {
                // User is trying to navigate to a restricted destination
                // Navigate them back to the previous fragment
                navController.navigate(R.id.selectLogin)
                // Display a message or perform any other action
                Toast.makeText(this, "Access Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun redirectHome(){
        if (FirebaseAuth.getInstance().currentUser != null) {
            // User is already logged in, so navigate them to a different destination
            navController.navigate(R.id.homeFragment) // Change this to the destination you prefer
        }
    }
}
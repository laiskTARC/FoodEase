package com.example.foodease

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.foodease.databinding.FragmentProfileBinding
import com.example.foodease.databinding.FragmentProfileInformationBinding
import com.example.foodease.ui.user.User
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileInformationFragment : Fragment() {
    private var _binding : FragmentProfileInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Show the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _binding = FragmentProfileInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the currently signed-in user
        val user = FirebaseAuth.getInstance().currentUser

        // Check if the user is signed in
        if (user != null) {
            val userId = user.uid

            // Initialize Firebase Realtime Database
            val db = Firebase.database
            val userRef = db.getReference("users").child(userId)

            // Read user data from the database
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Parse and display user data
                        val userProfile = dataSnapshot.getValue(User::class.java)
                        if (userProfile != null) {
                            binding.textViewProfileName.text = userProfile.name
                            binding.textViewProfileEmail.text = userProfile.email
                            binding.textViewProfileContact.text = userProfile.contact
                            binding.textViewProfileAddress.text = userProfile.address
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                    Log.e(TAG, "Error reading user data from Firebase Database", databaseError.toException())
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_profile, menu) // Replace with your menu resource
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Hide the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
package com.example.foodease

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodease.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = FirebaseAuth.getInstance()
        if(user != null){
            binding.textViewName.text = user.currentUser?.displayName.toString()
            binding.textViewEmail.text = user.currentUser?.email.toString()
        }

        binding.containerProfileInformation.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_profileInformationFragment)
        }

        binding.containerHistory.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_eventHistoryFragment)
        }

        binding.containerLogOut.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_profileFragment_to_selectLogin)
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                Log.d("Authentication", "User is logged in: ${currentUser.email}")

            } else {
                Log.d("Authentication", "User is not logged in")

            }
        }
    }
}
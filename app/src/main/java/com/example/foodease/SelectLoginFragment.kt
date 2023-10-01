package com.example.foodease

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.foodease.databinding.FragmentSelectLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class SelectLoginFragment : Fragment() {

    private var _binding : FragmentSelectLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectLoginBinding.inflate(inflater, container, false)

        val buttonSignIn = binding.buttonSignIn
        val buttonSignUp = binding.buttonSignUp

        // Hide the toolbar
        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        // Hide the bottom navigation view
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavAdmin)
        bottomNavigationView?.visibility = View.GONE


        buttonSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_selectLogin_to_loginFragment)
        }


        buttonSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_selectLogin_to_registerFragment)
        }

        return binding.root
    }
}
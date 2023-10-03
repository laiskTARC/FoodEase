package com.example.foodease

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.foodease.databinding.FragmentEventDetailBinding
import com.example.foodease.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //View Binding
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

        // Show the toolbar
        (activity as AppCompatActivity?)?.supportActionBar?.show()

        //Hide the bottom navigation view
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavAdmin)
        bottomNavigationView?.visibility = View.GONE



        // Hide the bottom navigation view
        //val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavAdmin)
        //bottomNavigationView?.visibility = View.GONE


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auth = Firebase.auth
        Log.i("startLogin", "Auth Result ${auth.currentUser}")
        val loginButton = binding.buttonAdminLogin
        loginButton.setOnClickListener {

            // Start
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty()) {
                Snackbar.make(view, "Email cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Snackbar.make(view, "Invalid email format", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Snackbar.make(view, "Password cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (password.length < 8) {
                Snackbar.make(view, "Password cannot be less than 8 characters", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val user = FirebaseAuth.getInstance().currentUser
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign-in was successful, retrieve the user object
                        val user = auth.currentUser
                        Log.i("firebaseLogin", "Auth Result $user")

                        if (user != null) {
                            // User is signed in, navigate to the desired destination
                            Log.i("startLogin", "Auth Result ${auth.currentUser}")
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        } else {
                            // User object is still null, handle the case if needed
                            Snackbar.make(
                                view,
                                "Authentication failed",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        // If sign-in fails, display a message to the user.
                        Snackbar.make(
                            view,
                            "Authentication failed",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

        }
    }


}
package com.example.foodease.ui.volunteer

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.foodease.databinding.FragmentVolunteerListAddBinding

class VolunteerListAddFragment : Fragment() {

    private var _binding : FragmentVolunteerListAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVolunteerListAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = binding.button3

        button.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val address = binding.addressEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()


            //Validate Name
            if (name.isEmpty()){
                Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Validate Address
            if(address.isEmpty()){
                Toast.makeText(context, "Address cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Validate Email
            if(email.isEmpty()){
                Toast.makeText(context, "Email cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

    }

    fun isValidVolunteer(volunteer: Volunteer) : Boolean{


        return false
    }
}
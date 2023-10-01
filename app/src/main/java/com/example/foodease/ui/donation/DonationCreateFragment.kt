package com.example.foodease.ui.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foodease.R
import com.example.foodease.databinding.FragmentDonationCreateBinding
import com.example.foodease.databinding.FragmentDonationListBinding
import com.example.foodease.databinding.FragmentEventCreateBinding
import com.example.foodease.ui.event.Event
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DonationCreateFragment : Fragment() {
    private var _binding : FragmentDonationCreateBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDonationCreateBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUpdate2.setOnClickListener {
        val name = binding.foodnameEditText.text.toString().trim()
        val description = binding.descEditText.text.toString().trim()
        val instruction = binding.instructionEditText.text.toString().trim()
        val quantity = binding.editTextTextQuantity.text.toString().toInt()

        // Database
        val database = Firebase.database
        val ref = database.getReference("donations") //Database Name
        val newChildRef = ref.push()
        val id = newChildRef.key?: ""

        val donation = Donation(id,name, description, instruction, quantity, "")

        newChildRef.setValue(donation).addOnSuccessListener {
            Snackbar.make(view, "Donation Successfully Created", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_donationCreateFragment_to_donationListFragment)
        }.addOnFailureListener{
            Snackbar.make(view, "Failed", Snackbar.LENGTH_SHORT).show()
        }
    }
    }

}
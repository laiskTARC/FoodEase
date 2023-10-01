package com.example.foodease.ui.donation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodease.R
import com.example.foodease.databinding.FragmentDonationListDetailBinding
import com.example.foodease.ui.volunteer.VolunteerViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class DonationListDetailFragment : Fragment() {

    private var _binding : FragmentDonationListDetailBinding? = null
    private val binding get() = _binding!!

    val donationViewModel: DonationViewModel by activityViewModels()

    private var id = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDonationListDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
/*
        val sharedPref = requireActivity().getSharedPreferences("donation_shared_pref", Context.MODE_PRIVATE)
        val name = sharedPref?.getString("name", "").toString().trim()
        val instruction = sharedPref?.getString("instruction", "").toString().trim()
        val quantity = sharedPref?.getString("quantity", "").toString().trim()
        val expiry = sharedPref?.getString("expiry", "").toString().trim()

        binding.textViewDataFoodName.text = name
        binding.textViewDataFoodInstruction.text = instruction
        binding.textViewDataExpiryDate.text = expiry
        binding.textViewDataFoodQuantity.text = quantity
*/
        donationViewModel.donations.observe(viewLifecycleOwner, { selectedItem ->
            id = selectedItem.id.toString()
            val name = selectedItem.name
            val expiry = selectedItem.expiryDate
            val instruction = selectedItem.instruction
            val quantity = selectedItem.quantity

            binding.textViewDataFoodName.text = name
            binding.textViewDataFoodInstruction.text = instruction
            binding.textViewDataExpiryDate.text = expiry.toString()
            binding.textViewDataFoodQuantity.text = quantity.toString()
        })

        binding.buttonDeleteDonation.setOnClickListener {
            val firebaseDatabase = Firebase.database
            val ref = firebaseDatabase.getReference("donations").child(id)

            ref.removeValue()
            Snackbar.make(binding.root, "Donation Successfully Deleted", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_donationListDetailFragment_to_donationListFragment)
        }


        val buttonAccept = view.findViewById<Button>(R.id.buttonAccept)
        val buttonReject = view.findViewById<Button>(R.id.buttonReject)
        val buttonUpdate = view.findViewById<Button>(R.id.buttonUpdate)

        buttonAccept.setOnClickListener {
            findNavController().navigate(R.id.action_donationListDetailFragment_to_donationListFragment)

        }
        buttonReject.setOnClickListener {
            findNavController().navigate(R.id.action_donationListDetailFragment_to_donationListFragment)
        }
        buttonUpdate.setOnClickListener {
            findNavController().navigate(R.id.action_donationListDetailFragment_to_donationListUpdateFragment)

        }
    }


}
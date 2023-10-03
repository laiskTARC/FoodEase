package com.example.foodease.ui.donation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.foodease.R
import com.example.foodease.databinding.FragmentDonationListDetailBinding
import com.example.foodease.databinding.FragmentDonationListUpdateBinding
import com.example.foodease.ui.volunteer.VolunteerViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class DonationListUpdateFragment : Fragment() {


    private var _binding : FragmentDonationListUpdateBinding? = null
    private val binding get() = _binding!!

    val donationViewModel: DonationViewModel by activityViewModels()

    private var id = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDonationListUpdateBinding.inflate(inflater, container, false)

        donationViewModel.donations.observe(viewLifecycleOwner, { selectedItem ->
            id = selectedItem.id.toString()
            val name = selectedItem.name
            val expiry = selectedItem.expiryDate
            val instruction = selectedItem.instruction
            val quantity = selectedItem.quantity

            binding.foodnameEditText.setText(name)
            binding.dateEditText.setText(instruction)
            binding.descEditText.setText("")
            binding.editTextTextQuatity.setText(quantity.toString())
        })

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_donation_list_update, container, false)

        val buttonBack = view.findViewById<Button>(R.id.buttonBack)

        val buttonUpdate2 = binding.buttonUpdate2

        buttonUpdate2.setOnClickListener {
            val firebaseDatabase = Firebase.database
            val ref = firebaseDatabase.getReference("donations")

            val newName = binding.foodnameEditText.text.toString()
            val newDescription = binding.descEditText.text.toString()
            val newInstruction = binding.toString()
            val newQuantity = binding.editTextTextQuatity.text.toString().toInt()

            val donation = mapOf(
                "name" to newName,
                "description" to newDescription,
                "instruction" to newInstruction,
                "quantity" to newQuantity,
            )

            ref.child(id).updateChildren(donation).addOnSuccessListener {
                Snackbar.make(binding.root, "Donation Successfully Updated", Snackbar.LENGTH_SHORT).show()
                Log.d(donation.toString(), ref.child(id).toString())
                findNavController().navigate(R.id.action_donationListUpdateFragment_to_donationListFragment)
            }.addOnFailureListener{
                Snackbar.make(binding.root, "Failed", Snackbar.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }


}
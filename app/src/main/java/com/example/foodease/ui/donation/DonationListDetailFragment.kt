package com.example.foodease.ui.donation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodease.R
import com.example.foodease.databinding.FragmentDonationListDetailBinding


class DonationListDetailFragment : Fragment() {

    private var _binding : FragmentDonationListDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DonationViewModel

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

        val sharedPref = requireActivity().getSharedPreferences("donation_shared_pref", Context.MODE_PRIVATE)
        val name = sharedPref?.getString("name", "").toString().trim()
        binding.textViewDataFoodName.text = name

        binding.textViewDataFoodInstruction
        binding.textViewExpiryDate
        binding.textViewFoodQuantity



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
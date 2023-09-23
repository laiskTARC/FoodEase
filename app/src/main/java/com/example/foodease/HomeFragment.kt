package com.example.foodease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import android.content.Intent
import android.net.Uri
import com.example.foodease.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val phone = "tel:01400114514"
        val address = "chinco-wm20@student.tarc.edu.my"


        binding.buttonCallUs.setOnClickListener{
            val contactIntent = Intent(Intent.ACTION_DIAL, Uri.parse(phone))
            startActivity(contactIntent)
        }

        binding.buttonEmailUs.setOnClickListener {
            val emailIntent : Intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + address))
            emailIntent.putExtra(Intent.EXTRA_EMAIL, address);

            startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
        }

        /*
        val donationButton = view.findViewById<Button>(R.id.donationButton)
        donationButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donationListFragment)
        }

        val inventoryButton = view.findViewById<Button>(R.id.inventoryButton)
        inventoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donationListFragment)
        }

        val requestButton = view.findViewById<Button>(R.id.requestButton)
        requestButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donationListFragment)
        }

        val volunteerButton = view.findViewById<Button>(R.id.volunteerButton)
        volunteerButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donationListFragment)
        }
*/
        return binding.root
    }


}
package com.example.foodease.ui.volunteer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodease.R
import com.example.foodease.databinding.FragmentVolunteerListBinding
import com.example.foodease.databinding.FragmentVolunteerListDetailBinding

class VolunteerListDetailFragment : Fragment() {

    private var _binding: FragmentVolunteerListDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // View Binding
        _binding = FragmentVolunteerListDetailBinding.inflate(inflater, container, false)

        // Retrieve the selected event
        val sharedPref = requireActivity().getSharedPreferences("volunteer_shared_pref", Context.MODE_PRIVATE)
        val name = sharedPref?.getString("name", "")

        binding.textViewVolunteerName.text = name

        return binding.root
    }
}
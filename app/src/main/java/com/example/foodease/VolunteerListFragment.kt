package com.example.foodease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodease.databinding.FragmentVolunteerListBinding


class VolunteerListFragment : Fragment() {
    private var _binding: FragmentVolunteerListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVolunteerListBinding.inflate(inflater, container, false)
        return binding.root


    }


}
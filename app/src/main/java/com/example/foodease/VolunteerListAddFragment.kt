package com.example.foodease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.foodease.database.Volunteer
import com.example.foodease.databinding.FragmentVolunteerListAddBinding


class VolunteerListAddFragment : Fragment() {
    private var _binding : FragmentVolunteerListAddBinding? = null

    private val binding get() = _binding!!
    private val volunteerViewModel: VolunteerViewModel
            by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVolunteerListAddBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSubmit2.setOnClickListener {
            val newVolunteer = Volunteer(
                binding.nameEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.phoneEditText.text.toString(),
                binding.addressEditText.text.toString(),
                1
            )

            volunteerViewModel.insert(newVolunteer)
            findNavController().navigateUp()
        }
    }


}
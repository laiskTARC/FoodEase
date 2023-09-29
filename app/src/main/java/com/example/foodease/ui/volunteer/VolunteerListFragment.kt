package com.example.foodease.ui.volunteer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodease.R
import com.example.foodease.databinding.FragmentVolunteerListBinding
import com.example.foodease.ui.event.EventAdapter
import com.example.foodease.ui.event.EventClickListener

class VolunteerListFragment : Fragment() ,VolunteerClickListener{
    private var _binding: FragmentVolunteerListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Hide the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        // View Binding
        _binding = FragmentVolunteerListBinding.inflate(inflater, container, false)

        val recyclerViewVolunteer = binding.recyclerViewVolunteer
        recyclerViewVolunteer.layoutManager = LinearLayoutManager(requireContext())

        val data = ArrayList<Volunteer>()

        data.add(Volunteer(0, "email", "contact", "address", "hi"))
        data.add(Volunteer(1, "Chin Chee Onn", "contact", "address", "hi"))
        data.add(Volunteer(1, "Chin Chee Onn", "contact", "address", "hi"))
        data.add(Volunteer(1, "Chin Chee Onn", "contact", "address", "hi"))
        data.add(Volunteer(1, "Chin Chee Onn", "contact", "address", "hi"))
        data.add(Volunteer(1, "Chin Chee Onn", "contact", "address", "hi"))


        val adapter = VolunteerAdapter(this)
        adapter.setVolunteer(data)

        recyclerViewVolunteer.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onVolunteerClick(eventId: Int?) {
        // Call FragmentB using the NavController
        findNavController().navigate(R.id.action_volunteerListFragment_to_volunteerListDetailFragment)
    }

}
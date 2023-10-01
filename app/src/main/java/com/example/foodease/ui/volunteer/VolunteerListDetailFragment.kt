package com.example.foodease.ui.volunteer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.foodease.R
import com.example.foodease.databinding.FragmentVolunteerListBinding
import com.example.foodease.databinding.FragmentVolunteerListDetailBinding
import com.example.foodease.ui.event.Event
import com.example.foodease.ui.event.EventViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class VolunteerListDetailFragment : Fragment() {

    private var _binding: FragmentVolunteerListDetailBinding? = null
    private val binding get() = _binding!!

    val volunteerViewModel: VolunteerViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Hide the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // View Binding
        _binding = FragmentVolunteerListDetailBinding.inflate(inflater, container, false)

        // Retrieve the selected event
        //val sharedPref = requireActivity().getSharedPreferences("volunteer_shared_pref", Context.MODE_PRIVATE)
        //val name = sharedPref?.getString("name", "")

        volunteerViewModel.volunteers.observe(viewLifecycleOwner, { selectedItem ->
            val id = selectedItem.id
            val name = selectedItem.name
            val email = selectedItem.email
            val address = selectedItem.address
            val contact = selectedItem.contact
            val gender = selectedItem.gender
            val birthday = selectedItem.birth

            binding.textViewDataName.text = name
            binding.textViewDataEmail.text = email
            binding.textViewDataContact.text = contact
            binding.textViewDataAddress.text = address
            binding.textViewDataDateOfBirth.text = birthday
            binding.textViewDataGender.text = gender

            //binding.textViewDataEnd.text = end
            //binding.textViewDataVolunteerRequired.text = volunteer.toString()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userStatus = volunteerViewModel.volunteers.value?.status

        val id = volunteerViewModel.volunteers.value?.id.toString()

        if (userStatus == "accepted" || userStatus == "rejected") {
            // If the user's status is "accepted" or "rejected", hide both buttons
            binding.buttonAcceptVolunteer.visibility = View.GONE
            binding.buttonRejectVolunteer.visibility = View.GONE
        } else {
            // If the user's status is not "accepted" or "rejected", show both buttons
            binding.buttonAcceptVolunteer.visibility = View.VISIBLE
            binding.buttonRejectVolunteer.visibility = View.VISIBLE

            // Set click listeners for the buttons
            binding.buttonAcceptVolunteer.setOnClickListener {
                updateVolunteerStatus(id, "accepted")
            }

            binding.buttonRejectVolunteer.setOnClickListener {
                rejectVolunteerStatus(id, "rejected")
            }
        }
    }

    private fun updateVolunteerStatus(id:String, newStatus: String){
        val firebaseDatabase = Firebase.database
        val ref = firebaseDatabase.getReference("volunteers").child(id)
        ref.child("status").setValue(newStatus)

        Snackbar.make(binding.root, "Accepted", Snackbar.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_volunteerListDetailFragment_to_volunteerListFragment)
    }

    private fun rejectVolunteerStatus(id:String, newStatus: String){
        val firebaseDatabase = Firebase.database
        val ref = firebaseDatabase.getReference("volunteers").child(id)
        ref.child("status").setValue(newStatus)

        Snackbar.make(binding.root, "Rejected", Snackbar.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_volunteerListDetailFragment_to_volunteerListFragment)
    }
}
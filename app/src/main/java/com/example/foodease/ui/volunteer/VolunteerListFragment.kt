package com.example.foodease.ui.volunteer

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodease.R
import com.example.foodease.databinding.FragmentVolunteerListBinding
import com.example.foodease.ui.event.EventAdapter
import com.example.foodease.ui.event.EventViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class VolunteerListFragment : Fragment(){
    private var _binding: FragmentVolunteerListBinding? = null
    private val binding get() = _binding!!

    val volunteerViewModel: VolunteerViewModel by activityViewModels()

    private val filteredVolunteers = ArrayList<Volunteer>()
    private val allVolunteers = ArrayList<Volunteer>()
    private lateinit var adapter: VolunteerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Hide the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        // View Binding
        _binding = FragmentVolunteerListBinding.inflate(inflater, container, false)


        // Remote Database
        val firebaseDatabase = Firebase.database
        val ref = firebaseDatabase.getReference("volunteers")
        val recyclerViewVolunteer = binding.recyclerViewVolunteer
        recyclerViewVolunteer.layoutManager = LinearLayoutManager(requireContext())

        val progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Loading ...")
        progressDialog.show()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                allVolunteers.clear()
                if(snapshot.exists()){
                    for(snapshot in snapshot.children){
                        val volunteer = snapshot.getValue(Volunteer::class.java)
                        allVolunteers.add(volunteer!!)
                    }

                    // Initially, show all volunteers
                    filteredVolunteers.clear()
                    filteredVolunteers.addAll(allVolunteers)

                    adapter = VolunteerAdapter{selectedItem->
                        volunteerViewModel.setSelected(selectedItem)

                        findNavController().navigate(R.id.action_volunteerListFragment_to_volunteerListDetailFragment)
                    }

                    adapter.setVolunteer(filteredVolunteers)
                    Log.d("a",filteredVolunteers.toString())
                    recyclerViewVolunteer.adapter = adapter

                    progressDialog.dismiss()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled event (e.g., log the error)
                Log.e("FirebaseError", "Database operation cancelled: ${error.message}")
            }
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewLabelFilter.text = "All"

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_filter_volunteer, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemAllVolunteer -> {
                // Show incoming volunteers
                val acceptedVolunteers = allVolunteers
                filteredVolunteers.clear()
                filteredVolunteers.addAll(allVolunteers)
                binding.textViewLabelFilter.text = "All"
            }

            R.id.itemIncoming -> {
                // Show incoming volunteers
                val acceptedVolunteers = allVolunteers.filter { it.status == "incoming" }
                filteredVolunteers.clear()
                filteredVolunteers.addAll(acceptedVolunteers)
                binding.textViewLabelFilter.text = "Incoming"
            }
            R.id.itemAccepted-> {
                // Show accepted volunteers
                val acceptedVolunteers = allVolunteers.filter { it.status == "accepted" }
                filteredVolunteers.clear()
                filteredVolunteers.addAll(acceptedVolunteers)
                binding.textViewLabelFilter.text = "Accepted"
            }
            R.id.itemRejected -> {
                // Show rejected volunteers
                val rejectedVolunteers = allVolunteers.filter { it.status == "rejected" }
                filteredVolunteers.clear()
                filteredVolunteers.addAll(rejectedVolunteers)
                binding.textViewLabelFilter.text = "Rejected"
            }
            else -> return super.onOptionsItemSelected(item)

        }

        // Notify the RecyclerView adapter of the data change
        adapter.notifyDataSetChanged()

        return true
    }

}
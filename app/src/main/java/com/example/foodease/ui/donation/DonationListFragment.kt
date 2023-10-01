package com.example.foodease.ui.donation

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodease.R
import com.example.foodease.databinding.FragmentDonationListBinding
import com.example.foodease.ui.event.EventViewModel
import com.example.foodease.ui.volunteer.VolunteerViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DonationListFragment : Fragment() {

    private var _binding : FragmentDonationListBinding? = null
    private val binding get() = _binding!!

    val donationViewModel: DonationViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDonationListBinding.inflate(inflater, container, false)

        // Hide the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val recyclerView = binding.recyclerViewDonation
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val data = ArrayList<Donation>()

        val progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Loading ...")
        progressDialog.show()

        // Remote Database
        val firebaseDatabase = Firebase.database

        val ref = firebaseDatabase.getReference("donations")

        val adapter = DonationAdapter{ selectedItem->
        donationViewModel.setSelectedDonation(selectedItem)

        findNavController().navigate(R.id.action_donationListFragment_to_donationListDetailFragment)
    }

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(snapshot in snapshot.children){
                        val donation = snapshot.getValue(Donation::class.java)
                        data.add(donation!!)
                    }
                    //val textViewE = binding.textView4
                    adapter.setDonation(data)

                    recyclerView.adapter = adapter

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

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_donation, menu)

        menu.findItem(R.id.itemAddDonation).setVisible(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.itemAddDonation -> {
                findNavController().navigate(R.id.action_donationListFragment_to_donationCreateFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
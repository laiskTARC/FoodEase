package com.example.foodease.ui.event

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.foodease.R
import com.example.foodease.databinding.FragmentEventDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File


class EventDetailFragment : Fragment() {

    private var _binding : FragmentEventDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventDetailBinding.inflate(inflater, container, false)

        // Show the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the selected event
        val sharedPref =
            requireActivity().getSharedPreferences("event_shared_pref", Context.MODE_PRIVATE)
        val name = sharedPref?.getString("name", "").toString().trim()
        val desc = sharedPref?.getString("description", "").toString().trim()
        val start = sharedPref?.getString("starting", "").toString().trim()
        val end = sharedPref?.getString("ending", "").toString().trim()
        val address = sharedPref?.getString("address", "").toString().trim()
        val volunteerRequired = sharedPref?.getString("volunteerRequired", "")
        val database = Firebase.database
        val ref = database.getReference("events")
        val id = sharedPref?.getString("id", "").toString()
        binding.editTextEventName.setText(name)


        binding.textViewDataDescription.text = desc
        binding.textViewDataVenueAddress.text = address
        binding.textViewDataStart.text = start
        binding.textViewDataEnd.text = end
        binding.textViewDataVolunteerRequired.text = volunteerRequired

        /*
        binding.buttonEdit.setOnClickListener {

            val newName = binding.editTextEventName.text.toString()
            val event = mapOf(
                "id" to id,
                "name" to newName
            )


            ref.child(id).updateChildren(event).addOnSuccessListener {
                binding.editTextEventName.text.clear()
                Toast.makeText(requireContext(), "successful", Toast.LENGTH_SHORT)
            }.addOnFailureListener{
                Toast.makeText(requireContext(), "fail", Toast.LENGTH_SHORT)
            }
        }
    }

         */

        /*
    private fun uploadPicture(){
        val filename = "profile.png"
        val file = File(this.context?.filesDir, filename)
        //Create an instance of Firebase Storage
        val firebaseStorage = Firebase.storage
        //Create a reference (a folder) to the storage
        val myRef = firebaseStorage.reference
            .child("profile")
            .child(profileViewModel.profile.value!!.phone)

        //Upload the file to firebase storage
        myRef.putFile(file.toUri())
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "File Uploaded",
                    Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { }
    }
*/
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_event_list, menu) // Replace with your menu resource
        menu.findItem(R.id.itemAddEvent).setVisible(false)
        menu.findItem(R.id.itemEditEventConfirm).setVisible(false)
        menu.findItem(R.id.itemDeleteEvent).setVisible(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

        when(item.itemId){

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        //Show the bottom navigation view
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavAdmin)
        bottomNavigationView?.visibility = View.VISIBLE
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
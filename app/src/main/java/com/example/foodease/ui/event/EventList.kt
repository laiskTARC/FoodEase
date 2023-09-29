package com.example.foodease.ui.event

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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodease.R
import com.example.foodease.databinding.FragmentEventListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EventList : Fragment(), EventClickListener {

    val eventViewModel: EventViewModel by viewModels()
    private var _binding : FragmentEventListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventListBinding.inflate(inflater, container, false)

        val progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Loading ...")
        progressDialog.show()

        val recyclerViewEvent = binding.recyclerViewEvent
        recyclerViewEvent.layoutManager = LinearLayoutManager(requireContext())
        val data = ArrayList<Event>()
        val name = ""

        // Remote Database
        val firebaseDatabase = Firebase.database

        val ref = firebaseDatabase.getReference("events")

        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(snapshot in snapshot.children){
                        val event = snapshot.getValue(Event::class.java)
                        data.add(event!!)
                    }

                    val adapter = EventAdapter(this@EventList)
                    //val textViewE = binding.textView4
                    adapter.setEvent(data)

                    recyclerViewEvent.adapter = adapter

                    progressDialog.dismiss()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled event (e.g., log the error)
                Log.e("FirebaseError", "Database operation cancelled: ${error.message}")
            }
        })



        //for (i in 1..5) {
        //data.add(Event(1,"Cedar Breaks Campground Host","1","1","1",1,"1"))
        //data.add(Event(2,"WoRiNi","1","1","1",1,"1"))
        //}



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_event_list, menu)

        menu.findItem(R.id.itemEditEvent).setVisible(false)
        menu.findItem(R.id.itemEditEventConfirm).setVisible(false)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.itemAddEvent -> {
                findNavController().navigate(R.id.action_eventFragment_to_eventCreateFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onEventClick(eventId: String?) {
        // Call FragmentB using the NavController
        findNavController().navigate(R.id.action_eventFragment_to_eventDetailFragment)
    }

}
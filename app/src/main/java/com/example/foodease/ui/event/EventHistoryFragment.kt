package com.example.foodease.ui.event

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodease.R
import com.example.foodease.databinding.FragmentEventDetailBinding
import com.example.foodease.databinding.FragmentEventHistoryBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class EventHistoryFragment : Fragment() {

    val eventViewModel: EventViewModel by viewModels()

    private var _binding : FragmentEventHistoryBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Hide the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Hide the bottom navigation view
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavAdmin)
        bottomNavigationView?.visibility = View.GONE

        _binding = FragmentEventHistoryBinding.inflate(inflater, container, false)
        val progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Loading ...")
        progressDialog.show()

        val recyclerViewEventHistory = binding.recyclerEventHistory
        recyclerViewEventHistory.layoutManager = LinearLayoutManager(requireContext())
        val data = ArrayList<Event>()

        val eventHistoryAdapter = EventHistoryAdapter()
        recyclerViewEventHistory.adapter = eventHistoryAdapter

        eventViewModel.eventList.observe(viewLifecycleOwner, Observer { events ->
            eventHistoryAdapter.setEventHistory(events)
            progressDialog.dismiss()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()

        //Show the bottom navigation view
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavAdmin)
        bottomNavigationView?.visibility = View.VISIBLE
    }
}
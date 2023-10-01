package com.example.foodease.ui.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodease.R

/**
 * A simple [Fragment] subclass.
 * Use the [RequestListDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RequestListDetailFragment : Fragment() {



    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_request_list_detail, container, false)

        val updateBtn = view.findViewById<Button>(R.id.updatebutton)
        val rejectBtn = view.findViewById<Button>(R.id.rejectbutton)

        updateBtn.setOnClickListener {
            findNavController().navigate(R.id.action_requestListDetailFragment_to_requestListUpdateFragment)
        }

        rejectBtn.setOnClickListener {
            findNavController().navigate(R.id.action_requestListDetailFragment_to_requestListFragment)
        }
        return view
    }

}
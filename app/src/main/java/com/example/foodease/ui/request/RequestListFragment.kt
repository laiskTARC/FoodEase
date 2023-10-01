package com.example.foodease.ui.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodease.R

class RequestListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_request_list, container, false)

        val assistanceDetail = view.findViewById<Button>(R.id.assistanceDetail)

        val addrequestButton = view.findViewById<Button>(R.id.addrequestbtn)

        assistanceDetail.setOnClickListener {
            findNavController().navigate(R.id.action_requestListFragment_to_requestListDetailFragment)
        }

        addrequestButton.setOnClickListener {
            findNavController().navigate(R.id.action_requestListFragment_to_requestListAddFragment)
        }

        return view
    }


}
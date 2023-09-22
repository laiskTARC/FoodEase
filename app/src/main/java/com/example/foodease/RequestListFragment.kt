package com.example.foodease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class RequestListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_request_list, container, false)

        val assistanceDetail = view.findViewById<Button>(R.id.assistanceDetail)

        assistanceDetail.setOnClickListener {
            findNavController().navigate(R.id.action_requestListFragment_to_requestListDetailFragment)
        }

        return view
    }


}
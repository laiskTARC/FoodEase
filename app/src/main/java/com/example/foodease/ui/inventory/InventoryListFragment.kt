package com.example.foodease.ui.inventory

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodease.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class InventoryListFragment : Fragment(){

    private lateinit var inventoryRecyclerView: RecyclerView
    private lateinit var inventoryList: ArrayList<Inventory>
    private lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inventory_list, container, false)

        inventoryRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewInventory)
        inventoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        //inventoryRecyclerView.setHasFixedSize(true)

        inventoryList = ArrayList<Inventory>()
        //val name = ""


        getInventoryData()


        val buttonAdd = view.findViewById<Button>(R.id.buttonAddInventory)
        //val buttonView = view.findViewById<Button>(R.id.buttonViewInventory)

        buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_inventoryListFragment_to_inventoryListAddFragment)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun getInventoryData(){
        inventoryRecyclerView.visibility = View.GONE
        dbRef = FirebaseDatabase.getInstance().getReference("inventory")
        dbRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               inventoryList.clear()
               if(snapshot.exists()){
                   for(inventorySnap in snapshot.children){
                       val inventoryData = inventorySnap.getValue(Inventory::class.java)
                       if(inventoryData != null){
                           inventoryList.add(inventoryData)
                       }
                   }

                   val inventoryAdapter = InventoryAdapter(inventoryList)
                   inventoryAdapter.setInventory(inventoryList)
                   inventoryRecyclerView.adapter = inventoryAdapter

                   inventoryRecyclerView.visibility = View.VISIBLE

               }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the database error here
                Log.e(TAG, "Database operation canceled: ${error.message}")

                // You can also show a message to the user indicating the error
                // For example, if you're using a Toast:
                Toast.makeText(context, "Database operation canceled: ${error.message}", Toast.LENGTH_SHORT).show()

                // You can also perform additional error-specific handling based on the error code
                if (error.code == DatabaseError.PERMISSION_DENIED) {
                    // Handle permission denied error
                    // For example, show a message to the user or take appropriate action
                } else if (error.code == DatabaseError.DISCONNECTED) {
                    // Handle disconnected error
                }
            }
        })

    }

    fun onInventoryClick(inventoryId: String?) {
        val args = Bundle()
        args.putString("InventoryId", inventoryId)

        // Navigate to the detail fragment with the provided arguments
        findNavController().navigate(R.id.action_inventoryListFragment_to_inventoryListDetailFragment, args)
    }


}
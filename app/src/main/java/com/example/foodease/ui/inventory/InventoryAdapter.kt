package com.example.foodease.ui.inventory

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodease.R

class InventoryAdapter(private val inventoryList: ArrayList<Inventory>): RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {
    private var dataList = emptyList<Inventory>()

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val inventoryName: TextView = itemView.findViewById(R.id.textViewInventoryName)
        val inventoryQuantity: TextView = itemView.findViewById(R.id.textViewInventoryQuantity)
        val inventoryDate: TextView = itemView.findViewById(R.id.textViewInventoryDate)

        init {
            itemView.setOnClickListener {
                //clickListener.onItemClick(adapterPosition)
            }
        }

    }

    internal fun setInventory(inventory: List<Inventory>){
        dataList = inventory
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.inventory_card,parent,false)
        return ViewHolder(itemView)
        //return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.inventory_card, parent, false))
    }

    override fun getItemCount() : Int{
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentInventory = dataList[position]
        //val id = currentInventory.id
        holder.inventoryName.findViewById<TextView>(R.id.textViewInventoryName)?.text = currentInventory.name
        holder.inventoryQuantity.findViewById<TextView>(R.id.textViewInventoryQuantity)?.text = currentInventory.quantity
        holder.inventoryDate.findViewById<TextView>(R.id.textViewInventoryDate)?.text = currentInventory.date

        val button = holder.itemView.findViewById<Button>(R.id.buttonViewInventory)

        button.setOnClickListener {
            val context = it.context
            val sharedPref = context.getSharedPreferences("inventory_shared_pref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            editor.putString("InventoryId", currentInventory.id)
            editor.putString("InventoryName", currentInventory.name)
            editor.putString("InventoryDesc", currentInventory.description)
            editor.putString("InventoryQuantity", currentInventory.quantity)
            editor.putString("InventoryDate", currentInventory.date)
            editor.apply()

            // Navigate to the detail fragment with arguments
            val args = Bundle()
            args.putString("InventoryId", currentInventory.id)
            args.putString("InventoryName", currentInventory.name)
            args.putString("InventoryDesc", currentInventory.description)
            args.putString("InventoryQuantity", currentInventory.quantity)
            args.putString("InventoryDate", currentInventory.date)

            it.findNavController().navigate(R.id.action_inventoryListFragment_to_inventoryListDetailFragment, args)
        }

    }

}
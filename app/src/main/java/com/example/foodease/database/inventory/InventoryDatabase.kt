package com.example.foodease.database.inventory

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodease.ui.inventory.Inventory

@Database(entities = arrayOf(Inventory::class), version = 1, exportSchema = false)
abstract class InventoryDatabase: RoomDatabase() {

    abstract fun inventoryDao(): InventoryDao

    companion object {
        //Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InventoryDatabase::class.java,
                    "inventory_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }


    }


}
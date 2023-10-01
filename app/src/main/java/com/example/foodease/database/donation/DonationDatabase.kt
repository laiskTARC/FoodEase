package com.example.foodease.database.donation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodease.ui.donation.Donation

@Database(entities = arrayOf(Donation::class), version = 1, exportSchema = false)
abstract class DonationDatabase: RoomDatabase() {
    abstract fun donationDao(): DonationDao

    companion object{
        //Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: DonationDatabase? = null

        fun getDatabase(context: Context): DonationDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DonationDatabase::class.java,
                    "donation_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
package com.example.foodease.database.volunteer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodease.ui.volunteer.Volunteer

@Database(entities = arrayOf(Volunteer::class), version = 1, exportSchema = false)
abstract class VolunteerDatabase: RoomDatabase() {
    abstract fun volunteerDao() : VolunteerDao

    companion object {
        //Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: VolunteerDatabase? = null

        fun getDatabase(context: Context): VolunteerDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VolunteerDatabase::class.java,
                    "contact_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }


    }

}
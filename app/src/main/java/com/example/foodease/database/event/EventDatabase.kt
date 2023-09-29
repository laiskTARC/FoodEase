package com.example.foodease.database.event

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodease.ui.event.Event

@Database(entities = arrayOf(Event::class), version = 1, exportSchema = false)
abstract class EventDatabase: RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object{
        //Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(context: Context): EventDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "event_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
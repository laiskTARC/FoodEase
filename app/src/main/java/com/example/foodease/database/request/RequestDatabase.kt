package com.example.foodease.database.request

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodease.database.request.RequestDao
import com.example.foodease.ui.request.Request

@Database(entities = arrayOf(Request::class), version = 1, exportSchema = false)
abstract class RequestDatabase: RoomDatabase() {
    abstract fun requestDao(): RequestDao

    companion object{
        //Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: RequestDatabase? = null

        fun getDatabase(context: Context): RequestDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RequestDatabase::class.java,
                    "request_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
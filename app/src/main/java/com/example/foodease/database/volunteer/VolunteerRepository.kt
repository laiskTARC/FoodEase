package com.example.foodease.database.volunteer

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.foodease.ui.volunteer.Volunteer

class VolunteerRepository(private val volunteerDao: VolunteerDao) {
    //Room execute all queries on a separate thread
    val allVolunteers: LiveData<List<Volunteer>> = volunteerDao.getAllVolunteer()

    @WorkerThread
    suspend fun insert(volunteer: Volunteer){
        volunteerDao.insert(volunteer)
    }

    @WorkerThread
    suspend fun delete(volunteer: Volunteer){
        volunteerDao.delete(volunteer)
    }

    @WorkerThread
    suspend fun deleteAll(){
        volunteerDao.deleteAll()
    }

    @WorkerThread
    suspend fun update(volunteer: Volunteer){
        volunteerDao.update(volunteer)
    }
}
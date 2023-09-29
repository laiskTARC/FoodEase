package com.example.foodease.database.donation

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.foodease.ui.donation.Donation

class DonationRepository(private val donationDao: DonationDao) {
    //Room execute all queries on a separate thread
    val allDonations: LiveData<List<Donation>> = donationDao.getAllDonation()

    @WorkerThread
    suspend fun insert(donation: Donation){
        donationDao.insert(donation)
    }

    @WorkerThread
    suspend fun delete(donation: Donation){
        donationDao.delete(donation)
    }

    @WorkerThread
    suspend fun update(donation: Donation){
        donationDao.update(donation)
    }
}
package com.example.foodease.database.donation

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodease.ui.donation.Donation

@Dao
interface DonationDao {
    @Query("SELECT * FROM donations ORDER BY name ASC")
    fun getAllDonation(): LiveData<List<Donation>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(donation: Donation)

    @Delete
    suspend fun delete(donation: Donation)

    @Update
    suspend fun update(donation: Donation)
}

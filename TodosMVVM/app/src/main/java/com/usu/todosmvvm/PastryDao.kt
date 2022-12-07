package com.usu.todosmvvm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.usu.todosmvvm.models.Pastry

@Dao
interface PastryDao {
    @Query("SELECT pastries FROM Pastry")
    suspend fun getPastryCount(): Int

    @Query("SELECT * FROM Pastry")
    suspend fun getAllPastries(): List<Pastry>

    @Insert
    suspend fun createPastry(pastry: Pastry)

    @Update
    suspend fun updatePastry(pastry: Pastry)

}
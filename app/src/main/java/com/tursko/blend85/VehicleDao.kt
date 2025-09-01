package com.tursko.blend85

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Insert
    suspend fun insert(vehicle: Vehicle)

    @Delete
    suspend fun delete(vehicle: Vehicle)

    @Query("SELECT * FROM vehicles ORDER BY display_name ASC")
    fun getAll(): Flow<List<Vehicle>>
}

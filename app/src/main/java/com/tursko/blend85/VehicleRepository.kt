package com.tursko.blend85

import kotlinx.coroutines.flow.Flow

class VehicleRepository (private val vehicleDao: VehicleDao) {

    val allVehicles: Flow<List<Vehicle>> = vehicleDao.getAll()

    suspend fun insert(vehicle: Vehicle) {
        vehicleDao.insert(vehicle)
    }

    suspend fun delete(vehicle: Vehicle) {
        vehicleDao.delete(vehicle)
    }
}
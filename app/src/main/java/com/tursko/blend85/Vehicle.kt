package com.tursko.blend85
import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles")
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "display_name")
    val displayName: String,

    @ColumnInfo(name = "tank_size_gallons")
    val tankSizeGallons: Double,

    @ColumnInfo(name = "tank_size_liters")
    val tankSizeLiters: Double,
)

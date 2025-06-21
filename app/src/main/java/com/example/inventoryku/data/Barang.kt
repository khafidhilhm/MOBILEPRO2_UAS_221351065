package com.example.inventoryku.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class Barang(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,
    val lokasi: String,
    val stok: Int,
    val catatan: String?
)

package com.example.inventoryku.data

import androidx.lifecycle.LiveData

class BarangRepository(private val barangDao: BarangDao) {

    val allBarang = barangDao.getAllBarang()

    suspend fun insert(barang: Barang) {
        barangDao.insert(barang)
    }

    suspend fun update(barang: Barang) {
        barangDao.update(barang)
    }

    suspend fun delete(barang: Barang) {
        barangDao.delete(barang)
    }

    fun searchBarang(keyword: String): LiveData<List<Barang>> {
        return barangDao.searchBarang(keyword)
    }
}

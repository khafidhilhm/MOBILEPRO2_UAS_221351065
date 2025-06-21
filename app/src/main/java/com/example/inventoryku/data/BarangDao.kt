package com.example.inventoryku.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BarangDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(barang: Barang)

    @Delete
    suspend fun delete(barang: Barang)

    @Query("SELECT * FROM barang ORDER BY id DESC")
    fun getAllBarang(): LiveData<List<Barang>>

    @Query("SELECT * FROM barang WHERE nama LIKE '%' || :keyword || '%' OR lokasi LIKE '%' || :keyword || '%' ORDER BY id DESC")
    fun searchBarang(keyword: String): LiveData<List<Barang>>

    @Update
    suspend fun update(barang: Barang)




}

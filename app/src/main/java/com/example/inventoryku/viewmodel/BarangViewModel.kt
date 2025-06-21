package com.example.inventoryku.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.inventoryku.data.Barang
import com.example.inventoryku.data.BarangDatabase
import com.example.inventoryku.data.BarangRepository
import kotlinx.coroutines.launch

class BarangViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BarangRepository

    init {
        val barangDao = BarangDatabase.getDatabase(application).barangDao()
        repository = BarangRepository(barangDao)
    }

    val allBarang = repository.allBarang

    fun insert(barang: Barang) = viewModelScope.launch {
        repository.insert(barang)
    }

    fun update(barang: Barang) = viewModelScope.launch {
        repository.update(barang)
    }

    fun delete(barang: Barang) = viewModelScope.launch {
        repository.delete(barang)
    }

    fun searchBarang(keyword: String): LiveData<List<Barang>> { // ✅ Tambahkan ini
        return repository.searchBarang(keyword)
    }
}

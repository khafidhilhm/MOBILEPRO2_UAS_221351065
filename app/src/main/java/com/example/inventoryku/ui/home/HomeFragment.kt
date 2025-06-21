package com.example.inventoryku.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventoryku.databinding.FragmentHomeBinding
import com.example.inventoryku.viewmodel.BarangViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: InventoryAdapter
    private val barangViewModel: BarangViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Inisialisasi adapter dengan click listener untuk edit
        adapter = InventoryAdapter(listOf()) { barang ->
            EditBarangDialog(barang).show(parentFragmentManager, "editDialog")
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Tampilkan semua data saat awal
        barangViewModel.allBarang.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }

        // Fitur pencarian
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val keyword = newText ?: ""
                barangViewModel.searchBarang(keyword).observe(viewLifecycleOwner) {
                    adapter.updateList(it)
                }
                return true
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

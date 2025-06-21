package com.example.inventoryku.ui.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.inventoryku.data.Barang
import com.example.inventoryku.databinding.FragmentAddBinding
import com.example.inventoryku.viewmodel.BarangViewModel

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val barangViewModel: BarangViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.btnSimpan.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val lokasi = binding.etLokasi.text.toString()
            val stok = binding.etStok.text.toString().toIntOrNull()
            val catatan = binding.etCatatan.text.toString()

            if (nama.isNotBlank() && lokasi.isNotBlank() && stok != null) {
                val barang = Barang(nama = nama, lokasi = lokasi, stok = stok, catatan = catatan)
                barangViewModel.insert(barang)
                Toast.makeText(context, "Barang disimpan", Toast.LENGTH_SHORT).show()
                binding.etNama.text?.clear()
                binding.etLokasi.text?.clear()
                binding.etStok.text?.clear()
                binding.etCatatan.text?.clear()
            } else {
                Toast.makeText(context, "Mohon isi semua field wajib", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

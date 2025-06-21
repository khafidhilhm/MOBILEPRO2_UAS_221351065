package com.example.inventoryku.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inventoryku.data.Barang
import com.example.inventoryku.databinding.ItemBarangBinding

class InventoryAdapter(
    private var list: List<Barang>,
    private val onItemClicked: (Barang) -> Unit
) : RecyclerView.Adapter<InventoryAdapter.BarangViewHolder>() {

    inner class BarangViewHolder(val binding: ItemBarangBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(barang: Barang) {
            binding.apply {
                tvNama.text = barang.nama
                tvLokasi.text = "Lokasi: ${barang.lokasi}"
                tvStok.text = "Stok: ${barang.stok}"
                tvCatatan.text = if (!barang.catatan.isNullOrBlank()) "Catatan: ${barang.catatan}" else ""
                root.setOnClickListener { onItemClicked(barang) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val binding = ItemBarangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BarangViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<Barang>) {
        list = newList
        notifyDataSetChanged()
    }
}

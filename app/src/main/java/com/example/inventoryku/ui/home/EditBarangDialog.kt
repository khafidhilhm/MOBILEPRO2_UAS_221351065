package com.example.inventoryku.ui.home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.inventoryku.data.Barang
import com.example.inventoryku.viewmodel.BarangViewModel

class EditBarangDialog(private val barang: Barang) : DialogFragment() {
    private val barangViewModel: BarangViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        val layout = layoutInflater.inflate(androidx.appcompat.R.layout.abc_alert_dialog_material, null)

        val etNama = EditText(context).apply { setText(barang.nama) }
        val etLokasi = EditText(context).apply { setText(barang.lokasi) }
        val etStok = EditText(context).apply { setText(barang.stok.toString()) }
        val etCatatan = EditText(context).apply { setText(barang.catatan ?: "") }

        val container = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            addView(etNama)
            addView(etLokasi)
            addView(etStok)
            addView(etCatatan)
            setPadding(32, 16, 32, 16)
        }

        return AlertDialog.Builder(context)
            .setTitle("Edit Barang")
            .setView(container)
            .setPositiveButton("Simpan") { _, _ ->
                val updated = barang.copy(
                    nama = etNama.text.toString(),
                    lokasi = etLokasi.text.toString(),
                    stok = etStok.text.toString().toIntOrNull() ?: 0,
                    catatan = etCatatan.text.toString()
                )
                barangViewModel.update(updated)
                Toast.makeText(context, "Barang diperbarui", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Hapus") { _, _ ->
                barangViewModel.delete(barang)
                Toast.makeText(context, "Barang dihapus", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("Batal", null)
            .create()
    }
}

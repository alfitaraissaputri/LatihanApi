package com.example.latihanapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.latihanapi.databinding.ItemCatatanBinding
import com.example.latihanapi.entities.Catatan

class CatatanAdapter(
    private val listCatatan: List<Catatan>,
    private val onItemClick: (Catatan) -> Unit // Fungsi untuk menangani klik
) : RecyclerView.Adapter<CatatanAdapter.ViewHolder>() {

    // 1. Menghubungkan layout item_catatan.xml
    class ViewHolder(val binding: ItemCatatanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCatatanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    // 2. Memasukkan data ke dalam komponen UI
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catatan = listCatatan[position]

        holder.binding.txtJudul.text = catatan.judul
        holder.binding.txtIsi.text = catatan.isi

        // Listener saat item diklik untuk Edit
        holder.itemView.setOnClickListener {
            onItemClick(catatan)
        }
    }

    // 3. Menghitung jumlah data
    override fun getItemCount(): Int = listCatatan.size
}
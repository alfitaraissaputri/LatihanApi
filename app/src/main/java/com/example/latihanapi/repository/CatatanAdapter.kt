package com.example.latihanapi.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.latihanapi.databinding.ItemCatatanBinding
import com.example.latihanapi.entities.Catatan

class CatatanAdapter(
    private var listCatatan: List<Catatan>,
    private val onItemClick: (Catatan) -> Unit
) : RecyclerView.Adapter<CatatanAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCatatanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCatatanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catatan = listCatatan[position]
        holder.binding.txtJudul.text = catatan.judul
        holder.binding.txtIsi.text = catatan.isi

        holder.itemView.setOnClickListener {
            onItemClick(catatan)
        }
    }

    override fun getItemCount(): Int = listCatatan.size

    // ⭐ Ini fungsi tambahan biar nanti bisa update data dari API
    fun updateData(data: List<Catatan>) {
        listCatatan = data
        notifyDataSetChanged()
    }
}

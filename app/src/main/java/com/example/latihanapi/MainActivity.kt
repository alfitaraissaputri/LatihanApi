package com.example.latihanapi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihanapi.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupEvents()
    }

    // Gunakan onResume agar data otomatis refresh saat kamu kembali dari halaman Tambah/Edit
    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            try {
                // Ambil data dari API melalui Retrofit
                val response = RetrofitClient.catatanRepository.getAllCatatan()

                if (response.isSuccessful) {
                    val listCatatan = response.body() ?: emptyList()

                    // Pasang Adapter ke RecyclerView
                    binding.rvCatatan.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = CatatanAdapter(listCatatan) { catatan ->
                            // AKSI KLIK: Pindah ke CreateCatatan untuk EDIT
                            val intent = Intent(this@MainActivity, CreateCatatan::class.java)
                            intent.putExtra("IS_EDIT", true)
                            intent.putExtra("ID", catatan.id)
                            intent.putExtra("JUDUL", catatan.judul)
                            intent.putExtra("ISI", catatan.isi)
                            startActivity(intent)
                        }
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Gagal ambil data: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupEvents() {
        // Klik tombol FAB untuk TAMBAH catatan baru
        binding.btnnavigate.setOnClickListener {
            val intent = Intent(this, CreateCatatan::class.java)
            intent.putExtra("IS_EDIT", false) // Beritahu ini bukan mode edit
            startActivity(intent)
        }
    }
}
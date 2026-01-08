package com.example.latihanapi


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.latihanapi.databinding.ActivityCreateCatatanBinding
import com.example.latihanapi.entities.Catatan

class CreateCatatan : AppCompatActivity() {
    private lateinit var binding: ActivityCreateCatatanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateCatatanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 🔥 PENTING: panggil event listener
        setupEvents()
    }

    fun setupEvents() {
        binding.tombolSimpan.setOnClickListener {
            val judul = binding.inputjudul.text.toString()
            val isi = binding.inputisi.text.toString()

            if (judul.isEmpty() || isi.isEmpty()) {
                displayMassage("Judul dan Isi tidak boleh kosong!")
                return@setOnClickListener
            }

            val payload = Catatan(
                id = null,
                judul = judul,
                isi = isi
            )
            lifecycleScope.launch {
                val response = RetrofitClient.catatanRepository.createCatatan(payload)
                if (response.isSuccessful) {
                    displayMassage("Catatan berhasil dibuat")

                    val intent = Intent(this@CreateCatatan,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    displayMassage("Gagal: ${response.code()}")

                }
            }
        }
    }

    fun displayMassage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

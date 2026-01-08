package com.example.latihanapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.latihanapi.repository.CatatanRepository

object RetrofitClient {

    private const val BASE_URL = "http://192.168.1.12:8000/api/"

    val catatanRepository: CatatanRepository by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatatanRepository::class.java)
    }
}

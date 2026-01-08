package com.example.latihanapi

import com.example.latihanapi.entities.Catatan
import retrofit2.Response
import retrofit2.http.*

interface CatatanRepository {

    // Mendapatkan semua data catatan (Read)
    @GET("catatan") // sesuaikan dengan endpoint API-mu
    suspend fun getAllCatatan(): Response<List<Catatan>>

    // Menambah catatan baru (Create)
    @POST("catatan")
    suspend fun createCatatan(@Body catatan: Catatan): Response<Unit>

    // Memperbarui catatan (Update)
    @PUT("catatan/{id}")
    suspend fun updateCatatan(
        @Path("id") id: Int,
        @Body catatan: Catatan
    ): Response<Unit>

    // Menghapus catatan (Delete) - Opsional
    @DELETE("catatan/{id}")
    suspend fun deleteCatatan(@Path("id") id: Int): Response<Unit>
}
package com.example.latihanapi.repository

import com.example.latihanapi.entities.Catatan
import retrofit2.Response
import retrofit2.http.*

interface CatatanRepository {
    @GET("catatan")
    suspend fun getAllCatatan(): Response<List<Catatan>>

    @POST("catatan")
    suspend fun createCatatan(@Body catatan: Catatan): Response<Unit>

    @PUT("catatan/{id}")
    suspend fun updateCatatan(
        @Path("id") id: Int,
        @Body catatan: Catatan
    ): Response<Unit>

    @DELETE("catatan/{id}")
    suspend fun deleteCatatan(@Path("id") id: Int): Response<Unit>
}

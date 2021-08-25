package com.softsquared.template.kotlin.src.main.region.network

import com.softsquared.template.kotlin.src.main.region.models.RoomInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RoomInfoInterface {

    @GET("/products/rooms")
    fun getRoomInfo(
        @Query("brandID") brandID: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
    ): Call<RoomInfoResponse>

}
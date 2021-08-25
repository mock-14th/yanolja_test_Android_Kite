package com.softsquared.template.kotlin.src.main.region.network

import com.softsquared.template.kotlin.src.main.region.models.DetailRoomInfoResponse
import com.softsquared.template.kotlin.src.main.region.models.RoomInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailRoomInterface {

    @GET("/products/rooms/room-detail")
    fun getRoomInfo(
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
        @Query("roomType") roomType: String,
        @Query("brandID") brandID: Int
    ): Call<DetailRoomInfoResponse>
}
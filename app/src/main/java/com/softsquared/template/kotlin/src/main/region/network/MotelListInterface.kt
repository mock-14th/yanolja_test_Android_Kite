package com.softsquared.template.kotlin.src.main.region.network

import retrofit2.Call
import com.softsquared.template.kotlin.src.main.region.models.MotelListResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MotelListInterface {

    @GET("/products/motels")
    fun getHotelList(
        @Query("region") region: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
        @Query("member") member: Int
    ): Call<MotelListResponse>
}
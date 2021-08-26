package com.softsquared.template.kotlin.src.main.region.network

import com.softsquared.template.kotlin.src.main.region.models.HotelListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HotelListInterface {

    @GET("/products/hotels")
    fun getHotelList(
        @Query("region") region: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
        @Query("member") member: Int
    ): Call<HotelListResponse>

}
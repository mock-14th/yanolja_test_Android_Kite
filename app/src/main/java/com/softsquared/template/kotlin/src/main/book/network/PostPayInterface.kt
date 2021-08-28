package com.softsquared.template.kotlin.src.main.book.network

import com.softsquared.template.kotlin.src.main.book.models.NightBookingResponse
import com.softsquared.template.kotlin.src.main.book.models.PostNightBookingRequest
import com.softsquared.template.kotlin.src.main.book.models.PostPayRequest
import com.softsquared.template.kotlin.src.main.book.models.PostPayResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface PostPayInterface {

    @POST("/app/booking/payment")
    fun postPay(
        @Query("bookedNum") bookedNum: Int,
        @Query("point") point: Int,
    ):Call<PostPayResponse>
}
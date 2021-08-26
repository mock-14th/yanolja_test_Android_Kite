package com.softsquared.template.kotlin.src.main.book.network

import com.softsquared.template.kotlin.src.main.book.models.GetUsersPhoneResponse
import com.softsquared.template.kotlin.src.main.book.models.NightBookingResponse
import com.softsquared.template.kotlin.src.main.book.models.PostNightBookingRequest
import com.softsquared.template.kotlin.src.main.register.models.PostSignUpRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostNightBookingInterface {

    @POST("/app/night-booking")
    fun postNightBooking(@Body params: PostNightBookingRequest): Call<NightBookingResponse>
}
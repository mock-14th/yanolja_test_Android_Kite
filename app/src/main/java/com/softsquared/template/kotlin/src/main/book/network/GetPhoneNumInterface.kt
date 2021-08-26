package com.softsquared.template.kotlin.src.main.book.network

import com.softsquared.template.kotlin.src.main.book.models.GetUsersPhoneResponse
import com.softsquared.template.kotlin.src.main.myPage.models.UserInfoResponse
import retrofit2.Call
import retrofit2.http.GET

interface GetPhoneNumInterface {
    @GET("/app/users/phone")
    fun getUserPhoneNum(): Call<GetUsersPhoneResponse>
}
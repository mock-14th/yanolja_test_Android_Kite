package com.softsquared.template.kotlin.src.main.register.network

import com.softsquared.template.kotlin.src.main.register.models.PostSignUpRequest
import com.softsquared.template.kotlin.src.main.register.models.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpRetrofitInterface {
    @POST("/app/users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>
}
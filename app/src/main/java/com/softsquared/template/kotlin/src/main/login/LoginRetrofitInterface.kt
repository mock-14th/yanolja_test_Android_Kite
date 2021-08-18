package com.softsquared.template.kotlin.src.main.login

import com.softsquared.template.kotlin.src.main.login.models.LoginResponse
import com.softsquared.template.kotlin.src.main.login.models.PostLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {

    @POST("/app/login")
    fun postLogin(@Body params: PostLoginRequest) : Call<LoginResponse>
}
package com.softsquared.template.kotlin.src.main.myPage.network
import android.renderscript.Sampler
import com.softsquared.template.kotlin.src.main.myPage.models.UserInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


interface UserInfoInterface {

    @GET("/app/my-yanolja")
    fun getUserInfo():Call<UserInfoResponse>

}
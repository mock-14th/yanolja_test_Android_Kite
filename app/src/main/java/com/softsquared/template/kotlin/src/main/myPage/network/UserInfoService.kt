package com.softsquared.template.kotlin.src.main.myPage.network

import android.util.Log
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.login.models.LoginResponse
import com.softsquared.template.kotlin.src.main.login.models.PostLoginRequest
import com.softsquared.template.kotlin.src.main.login.network.LoginRetrofitInterface
import com.softsquared.template.kotlin.src.main.myPage.models.GetUsersInfo
import com.softsquared.template.kotlin.src.main.myPage.models.UserInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoService(val view:MyPageFragmentView) {

    private val TAG = "tag"

    fun tryGetUserInfo(){
        val userInfoInterface = ApplicationClass.sRetrofit.create(UserInfoInterface::class.java)
        userInfoInterface.getUserInfo().enqueue(object : Callback<UserInfoResponse> {
            override fun onResponse(call: Call<UserInfoResponse>, response: Response<UserInfoResponse>) {
                view.onGetUserInfoSuccess(response.body() as UserInfoResponse)
            }

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                view.onGetUserInfoFailure(t.message ?: "통신 오류")
            }
        })
    }
}
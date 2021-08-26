package com.softsquared.template.kotlin.src.main.book.network

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.book.models.GetUsersPhoneResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetUserPhoneNumService(val view: UserPhoneNumFragmentView) {

    private val TAG = "tag"

    fun tryGetUserPhoneNum(){
        val getPhoneNumInterface = ApplicationClass.sRetrofit.create(GetPhoneNumInterface::class.java)
        getPhoneNumInterface.getUserPhoneNum().enqueue(object : Callback<GetUsersPhoneResponse> {

            override fun onResponse(
                call: Call<GetUsersPhoneResponse>,
                response: Response<GetUsersPhoneResponse>
            ) {
                view.onGetPhoneNumSuccess(response.body() as GetUsersPhoneResponse)
            }

            override fun onFailure(call: Call<GetUsersPhoneResponse>, t: Throwable) {
                view.onGetPhoneNumFailure(t.message ?: "통신 오류")
            }
        })
    }
}

interface UserPhoneNumFragmentView {

    fun onGetPhoneNumSuccess(response: GetUsersPhoneResponse)

    fun onGetPhoneNumFailure(message: String)
}
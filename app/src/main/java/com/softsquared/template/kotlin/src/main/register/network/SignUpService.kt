package com.softsquared.template.kotlin.src.main.register.network

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.register.models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpService (val view: SignUpFragmentView){

    fun tryPostSignUp(postSignUpRequest: com.softsquared.template.kotlin.src.main.register.models.PostSignUpRequest){
        val signUpRetrofitInterface = ApplicationClass.sRetrofit.create(SignUpRetrofitInterface::class.java)
        signUpRetrofitInterface.postSignUp(postSignUpRequest).enqueue(object :
            Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                view.onPostSignUpSuccess(response.body() as SignUpResponse)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                view.onPostSignUpFailure(t.message ?: "통신 오류")
            }
        })
    }

}
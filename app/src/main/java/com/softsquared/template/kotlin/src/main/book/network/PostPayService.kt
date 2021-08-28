package com.softsquared.template.kotlin.src.main.book.network

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.book.models.NightBookingResponse
import com.softsquared.template.kotlin.src.main.book.models.PostNightBookingRequest
import com.softsquared.template.kotlin.src.main.book.models.PostPayRequest
import com.softsquared.template.kotlin.src.main.book.models.PostPayResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostPayService(val view: PayFragmentView) {

    private val TAG = "tag"

    fun tryPostPay(bookedNum:Int,point:Int) {
        val postPayInterface =
            ApplicationClass.sRetrofit.create(PostPayInterface::class.java)
        postPayInterface.postPay(bookedNum,point)
            .enqueue(object : Callback<PostPayResponse> {
                override fun onResponse(
                    call: Call<PostPayResponse>,
                    response: Response<PostPayResponse>
                ) {
                    view.onPostPaySuccess(response.body() as PostPayResponse)
                }

                override fun onFailure(call: Call<PostPayResponse>, t: Throwable) {
                    view.onPostPayFailure(t.message ?: "통신 오류")
                }

            })
    }

}

interface PayFragmentView {

    fun onPostPaySuccess(response: PostPayResponse)

    fun onPostPayFailure(message: String)
}
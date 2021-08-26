package com.softsquared.template.kotlin.src.main.book.network

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.book.models.GetUsersPhoneResponse
import com.softsquared.template.kotlin.src.main.book.models.NightBookingResponse
import com.softsquared.template.kotlin.src.main.book.models.PostNightBookingRequest
import com.softsquared.template.kotlin.src.main.register.models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostNightBookingService(val view: NightBookingFragmentView) {

    private val TAG = "tag"

    fun tryPostNightBooking(postNightBookingRequest: PostNightBookingRequest) {
        val postNightBookingInterface =
            ApplicationClass.sRetrofit.create(PostNightBookingInterface::class.java)
        postNightBookingInterface.postNightBooking(postNightBookingRequest)
            .enqueue(object : Callback<NightBookingResponse> {
                override fun onResponse(
                    call: Call<NightBookingResponse>,
                    response: Response<NightBookingResponse>
                ) {
                    view.onPostBookingSuccess(response.body() as NightBookingResponse)
                }

                override fun onFailure(call: Call<NightBookingResponse>, t: Throwable) {
                    view.onPostBookingFailure(t.message ?: "통신 오류")
                }

            })
    }

}

interface NightBookingFragmentView {

    fun onPostBookingSuccess(response: NightBookingResponse)

    fun onPostBookingFailure(message: String)
}
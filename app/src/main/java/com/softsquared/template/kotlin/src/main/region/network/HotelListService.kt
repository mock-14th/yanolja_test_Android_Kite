package com.softsquared.template.kotlin.src.main.region.network

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.region.models.HotelListResponse
import com.softsquared.template.kotlin.src.main.region.models.MotelListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelListService(val view:HotelListFragmentView){

    fun tryGetHotelList(region: Int, startDate: String, endDate: String, member: Int) {
        val hotelListRetrofitInterface =
            ApplicationClass.sRetrofit.create(HotelListInterface::class.java)
        hotelListRetrofitInterface.getHotelList(region, startDate, endDate, member).enqueue(object :
            Callback<HotelListResponse> {
            override fun onResponse(
                call: Call<HotelListResponse>,
                response: Response<HotelListResponse>
            ) {
                view.onGetHotelListSuccess(response.body() as HotelListResponse)
            }

            override fun onFailure(call: Call<HotelListResponse>, t: Throwable) {
                view.onGetHotelListFailure(t.message ?: "통신 오류")
            }
        })
    }

}
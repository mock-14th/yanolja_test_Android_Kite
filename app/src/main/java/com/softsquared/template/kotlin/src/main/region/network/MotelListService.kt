package com.softsquared.template.kotlin.src.main.region.network

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.region.models.MotelListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MotelListService(val view:MotelListFragmentView) {

    fun tryGetHotelList(result:Int,startDate:String,endDate:String,member:Int){
        val motelListRetrofitInterface = ApplicationClass.sRetrofit.create(MotelListInterface::class.java)
        motelListRetrofitInterface.getMotelList(result,startDate,endDate,member).enqueue(object :
            Callback<MotelListResponse> {
            override fun onResponse(
                call: Call<MotelListResponse>,
                response: Response<MotelListResponse>
            ) {
                view.onGetMotelListSuccess(response.body() as MotelListResponse)
            }

            override fun onFailure(call: Call<MotelListResponse>, t: Throwable) {
                view.onGetMotelListFailure(t.message ?: "통신 오류")
            }
        })
    }
}
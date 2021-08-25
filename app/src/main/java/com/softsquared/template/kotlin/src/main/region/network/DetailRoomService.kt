package com.softsquared.template.kotlin.src.main.region.network

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.region.models.DetailRoomInfoResponse
import com.softsquared.template.kotlin.src.main.region.models.RoomInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRoomService(val view: DetailRoomFragmentView) {

    fun tryGetDetailRoomInfo(startDate: String, endDate: String, roomType:String, bradID:Int) {
        val detailRoomRetrofitInterface =
            ApplicationClass.sRetrofit.create(DetailRoomInterface::class.java)
        detailRoomRetrofitInterface.getRoomInfo(startDate, endDate, roomType, bradID).enqueue(object :
            Callback<DetailRoomInfoResponse> {

            override fun onResponse(
                call: Call<DetailRoomInfoResponse>,
                response: Response<DetailRoomInfoResponse>
            ) {
                view.onGetDetailRoomSuccess(response.body() as DetailRoomInfoResponse)
            }

            override fun onFailure(call: Call<DetailRoomInfoResponse>, t: Throwable) {
                view.onGetDetailRoomFailure(t.message ?: "통신 오류")
            }
        })
    }

}
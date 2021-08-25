package com.softsquared.template.kotlin.src.main.region.network

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.region.models.MotelListResponse
import com.softsquared.template.kotlin.src.main.region.models.RoomInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomInfoService(val view: RoomInfoFragmentView) {

    fun tryGetRoomInfoList(bradID: Int, startDate: String, endDate: String) {
        val roomInfoRetrofitInterface =
            ApplicationClass.sRetrofit.create(RoomInfoInterface::class.java)
        roomInfoRetrofitInterface.getRoomInfo(bradID, startDate, endDate).enqueue(object :
            Callback<RoomInfoResponse> {

            override fun onResponse(
                call: Call<RoomInfoResponse>,
                response: Response<RoomInfoResponse>
            ) {
                view.onGetRoomInfoSuccess(response.body() as RoomInfoResponse)
            }

            override fun onFailure(call: Call<RoomInfoResponse>, t: Throwable) {
                view.onGetRoomInfoFailure(t.message ?: "통신 오류")
            }
        })
    }

}
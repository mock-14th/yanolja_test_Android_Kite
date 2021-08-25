package com.softsquared.template.kotlin.src.main.region.network

import com.softsquared.template.kotlin.src.main.region.models.MotelListResponse
import com.softsquared.template.kotlin.src.main.region.models.RoomInfoResponse

interface RoomInfoFragmentView {

    fun onGetRoomInfoSuccess(response: RoomInfoResponse)

    fun onGetRoomInfoFailure(message: String)
}
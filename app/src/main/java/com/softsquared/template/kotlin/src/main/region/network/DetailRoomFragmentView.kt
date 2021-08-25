package com.softsquared.template.kotlin.src.main.region.network

import com.softsquared.template.kotlin.src.main.region.models.DetailRoomInfoResponse

interface DetailRoomFragmentView {

    fun onGetDetailRoomSuccess(response: DetailRoomInfoResponse)

    fun onGetDetailRoomFailure(message: String)

}
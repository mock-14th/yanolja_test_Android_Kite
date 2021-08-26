package com.softsquared.template.kotlin.src.main.region.network

import com.softsquared.template.kotlin.src.main.region.models.HotelListResponse
import com.softsquared.template.kotlin.src.main.region.models.MotelListResponse

interface HotelListFragmentView {

    fun onGetHotelListSuccess(response: HotelListResponse)

    fun onGetHotelListFailure(message: String)

}
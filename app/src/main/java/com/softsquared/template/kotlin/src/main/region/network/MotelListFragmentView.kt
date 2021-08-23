package com.softsquared.template.kotlin.src.main.region.network

import com.softsquared.template.kotlin.src.main.region.models.MotelListResponse

interface MotelListFragmentView {

    fun onGetHotelListSuccess(response: MotelListResponse)

    fun onGetHotelListFailure(message: String)

}
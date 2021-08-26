package com.softsquared.template.kotlin.src.main.region.models

import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.config.BaseResponse

data class HotelListResponse(
//    val code: Int,
//    val isSuccess: Boolean,
//    val message: String,
    @SerializedName("result") val result: ArrayList<ResultHotelList>
):BaseResponse()
package com.softsquared.template.kotlin.src.main.region.models

import com.google.gson.annotations.SerializedName

data class MotelListResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ArrayList<ResultHotelList>
)
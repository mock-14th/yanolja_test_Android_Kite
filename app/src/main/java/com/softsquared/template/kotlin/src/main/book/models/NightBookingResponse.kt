package com.softsquared.template.kotlin.src.main.book.models

import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.config.BaseResponse
import com.softsquared.template.kotlin.src.main.login.models.ResultJwt

data class NightBookingResponse(
//    val code: Int,
//    val isSuccess: Boolean,
//    val message: String,
    @SerializedName("result") val result : List<ResultNightBooking>,
):BaseResponse()
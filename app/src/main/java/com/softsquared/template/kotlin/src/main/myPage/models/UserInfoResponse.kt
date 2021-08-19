package com.softsquared.template.kotlin.src.main.myPage.models

import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.config.BaseResponse

data class UserInfoResponse(
//    val code: Int,
//    val isSuccess: Boolean,
//    val message: String,
    @SerializedName("result") val result: ResultInfo
):BaseResponse()
package com.softsquared.template.kotlin.src.main.book.models

import com.softsquared.template.kotlin.config.BaseResponse

data class GetUsersPhoneResponse(
//    val code: Int,
//    val isSuccess: Boolean,
//    val message: String,
    val result: List<ResultPhoneNum>
):BaseResponse()
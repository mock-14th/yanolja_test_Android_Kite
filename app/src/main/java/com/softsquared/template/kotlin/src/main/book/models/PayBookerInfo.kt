package com.softsquared.template.kotlin.src.main.book.models

import com.google.gson.annotations.SerializedName

data class PayBookerInfo(
    @SerializedName("예약자정보") val userInfo: String,
)
package com.softsquared.template.kotlin.src.main.book.models

import com.google.gson.annotations.SerializedName

data class ResultNightBooking(
    @SerializedName("bookedNum") val bookedNum: Int,
    @SerializedName("사용가능한 포인트") val useAblePoint: Int,
)
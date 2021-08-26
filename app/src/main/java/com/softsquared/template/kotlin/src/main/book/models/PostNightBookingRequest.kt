package com.softsquared.template.kotlin.src.main.book.models

import com.google.gson.annotations.SerializedName

data class PostNightBookingRequest(
    @SerializedName("brandName") val brandName: String,
    @SerializedName("endDate") val endDate: String,
    @SerializedName("isWalk") val isWalk: String,
    @SerializedName("memName") val memName: String,
    @SerializedName("payKind") val payKind: String,
    @SerializedName("roomType") val roomType: String,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("userPhone") val userPhone: String,
)
package com.softsquared.template.kotlin.src.main.book.models

import com.google.gson.annotations.SerializedName

data class PayGoodsAndUseInfo(

    @SerializedName("brandName") val brandName: String,
    @SerializedName("roomOption") val roomOption: String,
    @SerializedName("roomType") val roomType: String,
    @SerializedName("방썸네일") val roomSumbnail: String,
    @SerializedName("예약요일") val bookedDate: String,
    @SerializedName("예약일시") val bookdedTime: String,
    @SerializedName("이용날짜") val usingDate: String,
    @SerializedName("체크인아웃") val checkInAndOut: String,
)
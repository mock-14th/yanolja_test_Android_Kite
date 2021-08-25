package com.softsquared.template.kotlin.src.main.region.models

import com.google.gson.annotations.SerializedName

data class Room(

    @SerializedName("brandName") val roomBrandName: String,
    @SerializedName("roomID") val roomID: Int,
    @SerializedName("기준인원") val roomGizoonCnt: Int,
    @SerializedName("방 대표 이미지") val roomImg: String,
    @SerializedName("방옵션") val roomOption: String,
    @SerializedName("방타입") val roomType: String,
    @SerializedName("숙박가격") val roomRentPrice: String,
    @SerializedName("체크인시간") val roomCheckInTime: String,
    @SerializedName("최대인원") val roomMaxCnt: Int
)
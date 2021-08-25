package com.softsquared.template.kotlin.src.main.region.models

import com.google.gson.annotations.SerializedName

data class ResultDetailRoom(

    @SerializedName("대실") val detailRent: String,
    @SerializedName("방옵션") val detailRoomOption: String,
    @SerializedName("방이미지") val detailRoomImg: String,
    @SerializedName("방타입") val detailRoomType: String,
    @SerializedName("숙박가격") val detailSleepPrice: String,
    @SerializedName("숙소명") val detailRoomName: String,
    @SerializedName("예약정보") val detailBookInfo: String,
    @SerializedName("인원") val detailPersonCnt: String,
    @SerializedName("체크아웃") val detailCheckOut: String,
    @SerializedName("체크인") val detailCheckIn: String,

)
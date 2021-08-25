package com.softsquared.template.kotlin.src.main.region.models

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("내용") val reviewContent: String,
    @SerializedName("닉네임") val reviewNickName: String,
    @SerializedName("별점") val reviewStarRate: Int,
    @SerializedName("사용한 방의 옵션") val reviewRoomOption: String,
    @SerializedName("사용한방") val reviewUsedRoom: String,
    @SerializedName("후기쓴날짜") val reviewWroteDay: String,
    @SerializedName("후기이미지") val reviewImg: String,
    @SerializedName("비품만족도") val reviewGoodsSatify: String,
)
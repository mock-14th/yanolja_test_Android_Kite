package com.softsquared.template.kotlin.src.main.region.models

import com.google.gson.annotations.SerializedName

data class ResultHotelList(
    @SerializedName("brandID") val brandID: Int,
    @SerializedName("대표사진") val hotelImg: String,
    @SerializedName("숙박가격") val hotelSleepPrice: String,
    @SerializedName("숙박업소명") val hotelName: String,
    @SerializedName("지역명") val hotelRegionName: String,
    @SerializedName("카테고리") val hotelCategory: String,
    @SerializedName("평균별점") val hotelAvgRate: String,
    @SerializedName("후기갯수") val hotelCommentCnt: Int,
)
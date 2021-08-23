package com.softsquared.template.kotlin.src.main.region.models

import com.google.gson.annotations.SerializedName

data class ResultHotelList(
    @SerializedName("대실가격") val rentPrice: Int,
    @SerializedName("대표사진") val mainPhoto: String,
    @SerializedName("숙박가격") val sleepPrcie: String,
    @SerializedName("숙박업소명") val hotelName: String,
    @SerializedName("숙소아이디") val roomId: Int,
    @SerializedName("카테고리") val category: String,
    @SerializedName("평균별점") val rateAverage: String,
    @SerializedName("후기갯수") val commentCnt: Int
)
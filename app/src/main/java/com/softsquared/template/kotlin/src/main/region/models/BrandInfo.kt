package com.softsquared.template.kotlin.src.main.region.models

import com.google.gson.annotations.SerializedName

data class BrandInfo(
    @SerializedName("비품만족도") val goodsSatify: String,
    @SerializedName("숙박업소명") val hotelName: String,
    @SerializedName("숙소소개") val motelIntroduce: String,
    @SerializedName("이용안내") val motelHowToUse: String,
    @SerializedName("전체이미지") val motelImg: String,
    @SerializedName("주소") val address: String,
    @SerializedName("친절도") val kind: String,
    @SerializedName("카테고리") val category: String,
    @SerializedName("편의성") val ease: String,
    @SerializedName("평균별점") val avgStartRate: String,
    @SerializedName("후기개수") val commentCnt: Int,
    @SerializedName("청결도") val clean: String,
)
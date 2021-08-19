package com.softsquared.template.kotlin.src.main.myPage.models

import com.google.gson.annotations.SerializedName

data class ResultInfo(

    @SerializedName("닉네임") val nickname: String,

    @SerializedName("야놀자코인") val coin: String,

    @SerializedName("장바구니") val basket: Int,

    @SerializedName("쿠폰함") val coupon: Int,

    @SerializedName("포인트") val point: Int,

)
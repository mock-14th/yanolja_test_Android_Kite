package com.softsquared.template.kotlin.src.main.book.models

import com.google.gson.annotations.SerializedName

data class ResultPay(
    @SerializedName("결과") val payOutCome: PayOutCome,
    @SerializedName("금액 및 할인 정보") val payPriceAndDiscountInfo: PayPriceAndDiscountInfo,
    @SerializedName("상품 및 이용정보") val payGoodsAndUseInfo : PayGoodsAndUseInfo,
    @SerializedName("예약자 정보") val payBookerInfo: PayBookerInfo,
    @SerializedName("이용자 정보") val payUserInfo: PayUserInfo,
)
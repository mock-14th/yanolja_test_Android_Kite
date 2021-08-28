package com.softsquared.template.kotlin.src.main.book.models

import com.google.gson.annotations.SerializedName

data class PayPriceAndDiscountInfo(
    @SerializedName("결제금액") val payPrice: String,
    @SerializedName("결제수단") val payBy: String,
    @SerializedName("사용포인트") val usePoint: Int,
    @SerializedName("예약상품") val bookGoods: String,
    @SerializedName("주문번호") val bookedNum: Int,
)
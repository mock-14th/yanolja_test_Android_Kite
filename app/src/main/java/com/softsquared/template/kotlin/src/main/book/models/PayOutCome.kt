package com.softsquared.template.kotlin.src.main.book.models

import com.google.gson.annotations.SerializedName

data class PayOutCome(
    @SerializedName("예약완료되었습니다.") val bookDone: String,
    @SerializedName("예약일시.") val bookedDate: String,
)
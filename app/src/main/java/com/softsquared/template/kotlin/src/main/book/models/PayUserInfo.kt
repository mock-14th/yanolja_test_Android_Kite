package com.softsquared.template.kotlin.src.main.book.models

import com.google.gson.annotations.SerializedName

data class PayUserInfo(
    @SerializedName("이용자정보") val userInfo: String,
)
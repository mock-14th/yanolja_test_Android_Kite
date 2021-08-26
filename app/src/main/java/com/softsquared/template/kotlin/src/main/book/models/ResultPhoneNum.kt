package com.softsquared.template.kotlin.src.main.book.models

import com.google.gson.annotations.SerializedName

data class ResultPhoneNum(
    @SerializedName("폰번호") val usersPhoneNum: String,
)
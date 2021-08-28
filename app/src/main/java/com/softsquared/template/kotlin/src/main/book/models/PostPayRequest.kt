package com.softsquared.template.kotlin.src.main.book.models

import com.google.gson.annotations.SerializedName

data class PostPayRequest(
    @SerializedName("bookedNum") val bookedNum: Int,
    @SerializedName("point") val point: Int,
)

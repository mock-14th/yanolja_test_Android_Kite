package com.softsquared.template.kotlin.src.main.login.models

import com.google.gson.annotations.SerializedName

data class ResultJwt(
    @SerializedName("jwt") val jwt: String,
    @SerializedName("userId") val userId: String
)
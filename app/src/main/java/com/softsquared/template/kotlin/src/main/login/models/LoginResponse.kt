package com.softsquared.template.kotlin.src.main.login.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("result") val result : ResultJwt,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)




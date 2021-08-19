package com.softsquared.template.kotlin.src.main.register.models

import com.google.gson.annotations.SerializedName

data class ResultSignUp(
    @SerializedName("jwt") val jwt: String,
    @SerializedName("닉네임") val nickname: String,
    @SerializedName("추가된 회원") val addUser: String
)
package com.softsquared.template.kotlin.src.main.myPage.models

import com.google.gson.annotations.SerializedName

data class GetUsersInfo (
    @SerializedName("x-access-token") val jwt: String
    )

// ? 일단 보류
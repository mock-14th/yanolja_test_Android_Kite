package com.softsquared.template.kotlin.src.main.region.models

import com.google.gson.annotations.SerializedName


data class ResultRoomList(

    @SerializedName("brandInfo") val brandInfo: List<BrandInfo>,
    @SerializedName("reviewList") val reviewList: List<Review>,
    @SerializedName("roomList") val roomList: List<Room>
)
package com.softsquared.template.kotlin.src.main.myPage.network

import com.softsquared.template.kotlin.src.main.myPage.models.UserInfoResponse

interface MyPageFragmentView {

    fun onGetUserInfoSuccess(response:UserInfoResponse)

    fun onGetUserInfoFailure(message: String)
}
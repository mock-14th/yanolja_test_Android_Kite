package com.softsquared.template.kotlin.src.main.region.network

import com.softsquared.template.kotlin.src.main.region.models.MotelListResponse

interface MotelListFragmentView {

    fun onGetMotelListSuccess(response: MotelListResponse)

    fun onGetMotelListFailure(message: String)

}
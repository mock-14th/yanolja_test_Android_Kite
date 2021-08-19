package com.softsquared.template.kotlin.src.main.register.network

import com.softsquared.template.kotlin.src.main.register.models.SignUpResponse

interface SignUpFragmentView {

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)
}
package com.softsquared.template.kotlin.src.main.login

import com.softsquared.template.kotlin.src.main.login.models.LoginResponse

interface LoginFragmentView {

    fun onPostLoginSuccess(response: LoginResponse)

    fun onPostLoginFailure(message: String)
}
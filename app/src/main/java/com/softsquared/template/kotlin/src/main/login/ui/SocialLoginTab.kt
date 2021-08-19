package com.softsquared.template.kotlin.src.main.login.ui

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentLoginWithSocialBinding

class SocialLoginTab:
    BaseFragment<FragmentLoginWithSocialBinding>(FragmentLoginWithSocialBinding::bind, R.layout.fragment_login_with_social) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
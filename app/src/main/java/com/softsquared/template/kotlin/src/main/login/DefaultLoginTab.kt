package com.softsquared.template.kotlin.src.main.login

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentLoginWithYanolzaBinding

class DefaultLoginTab:
    BaseFragment<FragmentLoginWithYanolzaBinding>(FragmentLoginWithYanolzaBinding::bind, R.layout.fragment_login_with_yanolza) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
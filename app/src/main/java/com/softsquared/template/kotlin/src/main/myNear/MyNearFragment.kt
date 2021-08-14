package com.softsquared.template.kotlin.src.main.myNear

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentMyNearBinding
import com.softsquared.template.kotlin.databinding.FragmentMyPageBinding
import com.softsquared.template.kotlin.databinding.FragmentRegionBinding

// 지역
class MyNearFragment :
    BaseFragment<FragmentMyNearBinding>(FragmentMyNearBinding::bind, R.layout.fragment_my_near) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
package com.softsquared.template.kotlin.src.main.like

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentLikeBinding
import com.softsquared.template.kotlin.databinding.FragmentMyPageBinding
import com.softsquared.template.kotlin.databinding.FragmentRegionBinding

// 지역
class LikeFragment :
    BaseFragment<FragmentLikeBinding>(FragmentLikeBinding::bind, R.layout.fragment_like) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
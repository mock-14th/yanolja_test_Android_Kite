package com.softsquared.template.kotlin.src.main.region

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentMyPageBinding
import com.softsquared.template.kotlin.databinding.FragmentRegionBinding

// 지역
class RegionFragment :
    BaseFragment<FragmentRegionBinding>(FragmentRegionBinding::bind, R.layout.fragment_region) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
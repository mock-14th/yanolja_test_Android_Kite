package com.softsquared.template.kotlin.src.main.region.ui

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentEmptyBinding
import com.softsquared.template.kotlin.databinding.FragmentTabNearStationBinding

class NearStationTabFrag : BaseFragment<FragmentTabNearStationBinding>(FragmentTabNearStationBinding::bind, R.layout.fragment_tab_near_station){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
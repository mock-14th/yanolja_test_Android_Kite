package com.softsquared.template.kotlin.src.main.home.ui

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentTabWeeklyHotelBinding
import com.softsquared.template.kotlin.databinding.FragmentTabWeeklyPensionBinding

class RecoWeeklyPensionFragment : BaseFragment<FragmentTabWeeklyPensionBinding>(
    FragmentTabWeeklyPensionBinding::bind, R.layout.fragment_tab_weekly_pension){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
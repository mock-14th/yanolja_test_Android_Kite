package com.softsquared.template.kotlin.src.main.home.ui

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentTabWeeklyMobileBinding
import com.softsquared.template.kotlin.databinding.FragmentTabWeeklyPensionBinding

class RecoWeeklyMobileFragment : BaseFragment<FragmentTabWeeklyMobileBinding>(
    FragmentTabWeeklyMobileBinding::bind, R.layout.fragment_tab_weekly_mobile){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
package com.softsquared.template.kotlin.src.main.region.ui

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentEmptyBinding
import com.softsquared.template.kotlin.databinding.FragmentTabSearchMapBinding

class SearchMapTabFrag : BaseFragment<FragmentTabSearchMapBinding>(FragmentTabSearchMapBinding::bind, R.layout.fragment_tab_search_map){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
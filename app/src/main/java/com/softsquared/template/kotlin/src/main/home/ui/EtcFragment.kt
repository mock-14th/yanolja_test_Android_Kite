package com.softsquared.template.kotlin.src.main.home.ui

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentEmptyBinding
import com.softsquared.template.kotlin.databinding.FragmentHomeLocalRoomBinding

class EtcFragment : BaseFragment<FragmentEmptyBinding>(FragmentEmptyBinding::bind, R.layout.fragment_empty){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
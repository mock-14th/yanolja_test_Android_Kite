package com.softsquared.template.kotlin.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentHomeBinding
import com.softsquared.template.kotlin.src.main.home.adapter.HomePagerAdapter
import com.softsquared.template.kotlin.src.main.home.ui.EtcFragment
import com.softsquared.template.kotlin.src.main.home.ui.LocalRoomFragment
import com.softsquared.template.kotlin.src.main.home.ui.RecommendFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home){

    private lateinit var myFragment: View
    private lateinit var viewPagers: ViewPager
    private lateinit var tabLayouts: TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewPager()
        binding.homeTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setUpViewPager() {
        viewPagers = binding.viewpager
        tabLayouts = binding.homeTabLayout

        var adapter = HomePagerAdapter(childFragmentManager)
        adapter.addFragment(RecommendFragment(), "추천")
        adapter.addFragment(LocalRoomFragment(), "국내숙소")
        adapter.addFragment(EtcFragment(),"즐길거리")
        adapter.addFragment(EtcFragment(),"교통/항공")
        adapter.addFragment(EtcFragment(),"해외여행")

        viewPagers!!.adapter = adapter
        tabLayouts!!.setupWithViewPager(viewPagers)
    }

}
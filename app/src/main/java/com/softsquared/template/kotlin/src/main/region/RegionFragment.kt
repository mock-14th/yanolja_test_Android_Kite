package com.softsquared.template.kotlin.src.main.region

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentMyPageBinding
import com.softsquared.template.kotlin.databinding.FragmentRegionBinding
import com.softsquared.template.kotlin.src.main.region.ui.NearStationTabFrag
import com.softsquared.template.kotlin.src.main.region.ui.RegionTabFrag
import com.softsquared.template.kotlin.src.main.region.ui.SearchMapTabFrag

// 지역
class RegionFragment :
    BaseFragment<FragmentRegionBinding>(FragmentRegionBinding::bind, R.layout.fragment_region) {

    private lateinit var drawable: GradientDrawable
    private lateinit var fragment: Fragment
    //private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // tablayout에 구분선을 넣기 위한 코드
        drawable = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(
                0XFFD98880.toInt(),
                0XFFD98880.toInt(),
                0XFFF4D03F.toInt(),
                0XFF48C9B0.toInt()
            ))

        val root = binding.regionTablayout.getChildAt(0)
        (root as LinearLayout).showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        drawable.setColor(resources.getColor(R.color.secondColor))
        drawable.setSize(2,1)
        //root.dividerPadding = 50
        root.setPadding(0,50,0,50)
        root.dividerDrawable = drawable

        //화면을 켰을 때 기본으로 보여지는 fragment 설정
        fragment = RegionTabFrag()
        fragmentTransaction = parentFragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.region_frame_layout,fragment)
        fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction!!.commit()

        // 각각의 탭들을 누를 때, framgent 변경
        binding.regionTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // creating cases for fragment
                when (tab.position) {
                    // '지역별' 탭 화면으로 변경
                    0 -> fragment = RegionTabFrag()

                    // '역주변' 탭 화면으로 변경
                    1 -> fragment = NearStationTabFrag()

                    // '지도 검색' 탭 화면으로 변경
                    2 -> fragment = SearchMapTabFrag()
                }
                val fm = parentFragmentManager
                val ft = fm.beginTransaction()
                ft.replace(R.id.region_frame_layout, fragment)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ft.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
}


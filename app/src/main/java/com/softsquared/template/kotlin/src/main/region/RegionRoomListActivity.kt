package com.softsquared.template.kotlin.src.main.region

import android.content.Intent
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityRegionRoomListBinding
import com.softsquared.template.kotlin.src.main.home.adapter.HomePagerAdapter
import com.softsquared.template.kotlin.src.main.home.ui.EtcFragment
import com.softsquared.template.kotlin.src.main.region.ui.MotelListFragment
import com.softsquared.template.kotlin.src.main.region.ui.RegionTabActivity

class RegionRoomListActivity : BaseActivity<ActivityRegionRoomListBinding>(ActivityRegionRoomListBinding::inflate) {

    private lateinit var viewPagers: ViewPager
    private lateinit var tabLayouts: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpRoomViewPager()
        binding.roomListTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })


        // 지역 클릭 리스너 -> 지역 리스트 다시 보여주기(but Fragment 가 아닌 Activity)
        binding.roomMiddlebarClOne.setOnClickListener {
            val intent = Intent(this, RegionTabActivity::class.java)
            startActivity(intent)
        }



    }

    private fun setUpRoomViewPager() {
        viewPagers = binding.roomListViewpager
        tabLayouts = binding.roomListTablayout

        var adapter = HomePagerAdapter(supportFragmentManager)
        adapter.addFragment(MotelListFragment(), "모텔")
        adapter.addFragment(EtcFragment(), "호텔/리조트")
        adapter.addFragment(EtcFragment(),"펜션/풀빌라")
        adapter.addFragment(EtcFragment(),"게스트하우스")
        adapter.addFragment(EtcFragment(),"#무한쿠폰룸")
        adapter.addFragment(EtcFragment(),"#뷰티크브랜드")
        adapter.addFragment(EtcFragment(),"#초특가모델")

        viewPagers!!.adapter = adapter
        tabLayouts!!.setupWithViewPager(viewPagers)
    }
}
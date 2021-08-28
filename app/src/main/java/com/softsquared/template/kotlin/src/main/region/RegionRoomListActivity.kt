package com.softsquared.template.kotlin.src.main.region

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityRegionRoomListBinding
import com.softsquared.template.kotlin.databinding.DialogSetDayAndCntBinding
import com.softsquared.template.kotlin.src.main.home.adapter.HomePagerAdapter
import com.softsquared.template.kotlin.src.main.home.ui.EtcFragment
import com.softsquared.template.kotlin.src.main.region.ui.*

class RegionRoomListActivity :
    BaseActivity<ActivityRegionRoomListBinding>(ActivityRegionRoomListBinding::inflate) {

    private lateinit var viewPagers: ViewPager
    private lateinit var tabLayouts: TabLayout
    private lateinit var binding2: DialogSetDayAndCntBinding
    private var regionCode: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent.hasExtra("region")){
            regionCode = intent.getIntExtra("region",1)
        }


        if(regionCode == 3){
            binding.roomListLocationTv.text = "서초/신사/방배"
        }else{
            binding.roomListLocationTv.text = "강남/역삼/삼성/논현"
        }

        // 프래그먼트로 올릴 다이얼로그 view
        binding2 = DialogSetDayAndCntBinding.inflate(layoutInflater)

        setUpRoomViewPager()
        binding.roomListTablayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
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

        // 인원 수 및 날짜 변경 리스너
        val settingBtn = binding.roomMiddlebarClTwo
        settingBtn.setOnClickListener {

//            // fragment 올리기
            val mBuilder = AlertDialog.Builder(this, R.style.MyDialogTheme).setView(binding2.root)
            // view의 중복 사용을 방지하기 위한 코드
            if (binding2.root.parent != null)
                (binding2.root.parent as ViewGroup).removeView(binding2.root)

            val mAlertDialog = mBuilder.show()

            // 날짜 선택 창 열기
            binding2.fragMiddlebarClOne.setOnClickListener {

                val intent = Intent(this, CalendarActivity::class.java)
                startActivityForResult(intent, 1000)
            }

            binding2.fragMiddlebarClTwo.setOnClickListener {
                val intent = Intent(this, PersonNumCntActivity::class.java)
                startActivityForResult(intent, 2000)
            }

            binding2.selectedBtn.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var date = ""
        var perCnt = ""

        if(resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                val startDate = data!!.getStringExtra("startDate")
                val endDate = data!!.getStringExtra("endDate")
                val showingDate = data!!.getStringExtra("showingDate")

                // 날짜 설정
                binding2.fragListCalendarTv.text = showingDate

                date = showingDate!!

            } else if(requestCode == 2000){

                val numCnt = data!!.getIntExtra("numCnt",1)

                // 명 수 설정
                binding2.fragListPersonTv.text = "성인 " + numCnt + ",아동 0"

                perCnt = numCnt.toString()
            }

            // 적용 화면 설정
            binding.roomListCalendarTv.text = "8월 18일 ~ 8월20일 · 1명"
        }

    }

    private fun setUpRoomViewPager() {
        viewPagers = binding.roomListViewpager
        tabLayouts = binding.roomListTablayout

        var adapter = HomePagerAdapter(supportFragmentManager)
        adapter.addFragment(MotelListFragment(regionCode), "모텔")
        adapter.addFragment(HotelListFragment(regionCode), "호텔/리조트")
        adapter.addFragment(EtcFragment(), "펜션/풀빌라")
        adapter.addFragment(EtcFragment(), "게스트하우스")
        adapter.addFragment(EtcFragment(), "#무한쿠폰룸")
        adapter.addFragment(EtcFragment(), "#뷰티크브랜드")
        adapter.addFragment(EtcFragment(), "#초특가모델")

        viewPagers!!.adapter = adapter
        tabLayouts!!.setupWithViewPager(viewPagers)
    }
}
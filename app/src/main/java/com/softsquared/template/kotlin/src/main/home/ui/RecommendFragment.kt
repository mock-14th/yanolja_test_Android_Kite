package com.softsquared.template.kotlin.src.main.home.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.dpToPx
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentHomeRecommendBinding
import com.softsquared.template.kotlin.src.main.home.adapter.*
import com.softsquared.template.kotlin.src.main.home.data.RecoMagazineData
import com.softsquared.template.kotlin.src.main.home.data.RecoNewHotData
import com.softsquared.template.kotlin.src.main.home.data.RecoSpriceData

class RecommendFragment : BaseFragment<FragmentHomeRecommendBinding>(FragmentHomeRecommendBinding::bind, R.layout.fragment_home_recommend){

    // NEW & HOT adpater
    private lateinit var nhAdapter:RecoNewHotAdapter

    // 오늘의특가 adpater
    private lateinit var spAdapter:RecoSpriceAdapter

    // 오늘의 인기 매거진 adpater
    private lateinit var magazineAdapter:RecoMagazineAdapter

    // viewpager 아이템 변경을 위한 변수
    private var currentPosition = 0

    //핸들러 설정
    //ui 변경하기
    val handler= Handler(Looper.getMainLooper()){
        setPage()
        true
    }

    // Weekly Tab
    private lateinit var myFragment: View
    private lateinit var viewPagers: ViewPager
    private lateinit var tabLayouts: TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //////////////////////////////// "NEW&HOT" 정보를 담는 배열 생성 /////////////////////////////////////
        val nhList = ArrayList<RecoNewHotData>()

        nhList.add(RecoNewHotData("고속버스",resources.getDrawable(R.drawable.home_reco_nh_bus)))
        nhList.add(RecoNewHotData("풀빌라",resources.getDrawable(R.drawable.home_reco_nh_bila)))
        nhList.add(RecoNewHotData("물놀이특가",resources.getDrawable(R.drawable.home_reco_nh_water)))
        nhList.add(RecoNewHotData("아이야놀자",resources.getDrawable(R.drawable.home_reco_nh_baby)))
        nhList.add(RecoNewHotData("호캉스패키지",resources.getDrawable(R.drawable.home_reco_nh_food)))
        nhList.add(RecoNewHotData("맛집",resources.getDrawable(R.drawable.home_reco_nh_dish)))

        nhAdapter = RecoNewHotAdapter(nhList, this, requireActivity())

        // 리사이클러 뷰 타입 설정
        val linearLayoutManager = LinearLayoutManager(requireActivity())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recoRvNH.layoutManager = linearLayoutManager

        // 만든 어댑터 recyclerview에 연결
        binding.recoRvNH.adapter = nhAdapter



        ///////////////////////////////////// 광고 viewpager ///////////////////////////////////////////
        //뷰페이저 넘기는 쓰레드
        val thread=Thread(PagerRunnable())
        thread.start()

        // viewpager adapter 연결
        binding.recoViewpager.adapter = RecoViewPagerAdapter(requireContext())



        //////////////////////////////////////// "오늘의 특가" 정보를 담는 배열 생성 //////////////////////////////////////////
        val spList = ArrayList<RecoSpriceData>()

        spList.add(RecoSpriceData("50%","소노 호텔&리조트","선착순 쿠폰 특가","5만원대",
            resources.getColor(R.color.special_one).toDrawable(),resources.getDrawable(R.drawable.reco_special_img_one)))

        spList.add(RecoSpriceData("30%","인기 펜션&풀빌라","선착순 쿠폰 특가","3만원대",
            resources.getColor(R.color.special_two).toDrawable(),resources.getDrawable(R.drawable.reco_special_img_two)))

        spList.add(RecoSpriceData("50%","베스킨라빈스","쿼터 아이스크림","10,000원",
            resources.getColor(R.color.special_three).toDrawable(),resources.getDrawable(R.drawable.reco_special_img_three)))

        spAdapter = RecoSpriceAdapter(spList,this,requireActivity())

        val linearLayoutManager2 = LinearLayoutManager(requireActivity())
        linearLayoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        // 리사이클러 뷰 타입 설정
        binding.recoRvPrice.layoutManager = linearLayoutManager2
        // 만든 어댑터 recyclerview에 연결
        binding.recoRvPrice.adapter = spAdapter


        //////////////////////////////////////////// 위클리 인기 Top //////////////////////////////////////////////
        setUpWeeklyViewPager()
        binding.weeklyPopularTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })

        /////////////////////////////////////// 오늘의 인기 매거진 ///////////////////////////////////////////////////
        val magazineList = ArrayList<RecoMagazineData>()
        magazineList.add(RecoMagazineData("숲 속 언택트 힐링 \n홍천 부티크 풀빌라", ContextCompat.getDrawable(requireContext(),R.drawable.img_magazine_one)!!))
        magazineList.add(RecoMagazineData("국내 1위로 뽑혔다는 \n강릉 오션뷰 풀빌라", ContextCompat.getDrawable(requireContext(),R.drawable.img_magazine_two)!!))
        magazineList.add(RecoMagazineData("1시간이면 갈 수 있는 \n근교 숲캉스 숙소", ContextCompat.getDrawable(requireContext(),R.drawable.img_magazine_three)!!))
        magazineList.add(RecoMagazineData("SNS에서 유명한 \n테마별 태안 숙소", ContextCompat.getDrawable(requireContext(),R.drawable.img_magazine_four)!!))

        val linearLayoutManager3 = LinearLayoutManager(requireActivity())
        linearLayoutManager3.orientation = LinearLayoutManager.HORIZONTAL

        magazineAdapter = RecoMagazineAdapter(magazineList,this,requireActivity())

        // 리사이클러 뷰 타입 설정
        binding.recoMagazineRv.layoutManager = linearLayoutManager3
        // 만든 어댑터 recyclerview에 연결
        binding.recoMagazineRv.adapter = magazineAdapter

    }

    //페이지 변경하기
    fun setPage(){
        if(currentPosition==5) currentPosition=0
        binding.recoViewpager.setCurrentItem(currentPosition,true)
        currentPosition+=1
    }

    //2초 마다 페이지 넘기기
    inner class PagerRunnable:Runnable{
        override fun run() {
            while(true){
                Thread.sleep(2000)
                handler.sendEmptyMessage(0)
            }
        }
    }

    private fun setUpWeeklyViewPager() {
        viewPagers = binding.weeklyPopularViewPager
        tabLayouts = binding.weeklyPopularTabLayout

        var adapter = RecoWeeklyViewPagerAdapter(requireFragmentManager()!!)
        adapter.addFragment(RecoWeeklyMobileFragment(), "모바일교환권")
        adapter.addFragment(RecoWeeklyPensionFragment(), "펜션")
        adapter.addFragment(RecoWeeklyHotelFragment(), "호텔")
        adapter.addFragment(EtcFragment(), "레저/티켓")
        adapter.addFragment(EtcFragment(), "모델")
        adapter.addFragment(EtcFragment(), "게스트하우스")

        viewPagers!!.adapter = adapter
        tabLayouts!!.setupWithViewPager(viewPagers)

        for (i in 0 until tabLayouts.tabCount) {
            val tab = (tabLayouts.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(dpToPx(5), 5, dpToPx(5), 5)
            tab.requestLayout()
        }
    }
}
package com.softsquared.template.kotlin.src.main.home.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentHomeRecommendBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.adapter.RecoNewHotAdapter
import com.softsquared.template.kotlin.src.main.home.adapter.RecoSpriceAdapter
import com.softsquared.template.kotlin.src.main.home.data.RecoNewHotData
import com.softsquared.template.kotlin.src.main.home.data.RecoSpriceData

class RecommendFragment : BaseFragment<FragmentHomeRecommendBinding>(FragmentHomeRecommendBinding::bind, R.layout.fragment_home_recommend){

    // NEW & HOT adpater
    private lateinit var nhAdapter:RecoNewHotAdapter

    // 오늘의특가 adpater
    private lateinit var spAdapter:RecoSpriceAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // "NEW&HOT" 정보를 담는 배열 생성
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

        // "오늘의 특가" 정보를 담는 배열 생성
        val spList = ArrayList<RecoSpriceData>()

        spList.add(RecoSpriceData("50%","소노 호텔&리조트","선착순 쿠폰 특가","5만원대",
            resources.getColor(R.color.special_one).toDrawable(),resources.getDrawable(R.drawable.reco_special_img_one)))

        spList.add(RecoSpriceData("50%","소노 호텔&리조트","선착순 쿠폰 특가","5만원대",
            resources.getColor(R.color.special_one).toDrawable(),resources.getDrawable(R.drawable.reco_special_img_one)))

        spList.add(RecoSpriceData("50%","소노 호텔&리조트","선착순 쿠폰 특가","5만원대",
            resources.getColor(R.color.special_one).toDrawable(),resources.getDrawable(R.drawable.reco_special_img_one)))

        spAdapter = RecoSpriceAdapter(spList,this,requireActivity())

        val linearLayoutManager2 = LinearLayoutManager(requireActivity())
        linearLayoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        // 리사이클러 뷰 타입 설정
        binding.recoRvPrice.layoutManager = linearLayoutManager2
        // 만든 어댑터 recyclerview에 연결
        binding.recoRvPrice.adapter = spAdapter

    }
}
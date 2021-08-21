package com.softsquared.template.kotlin.src.main.home.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentHomeLocalRoomBinding
import com.softsquared.template.kotlin.src.main.home.adapter.LocalRecentAdapter
import com.softsquared.template.kotlin.src.main.home.adapter.RecoMagazineAdapter
import com.softsquared.template.kotlin.src.main.home.data.LocalRecentData

class LocalRoomFragment : BaseFragment<FragmentHomeLocalRoomBinding>(FragmentHomeLocalRoomBinding::bind, R.layout.fragment_home_local_room){

    private lateinit var recentAdapter: LocalRecentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ////////////////////////////////////// 최근 본 상품의 연관상품 ///////////////////////////////////////////
        val recentList = ArrayList<LocalRecentData>()

        recentList.add(LocalRecentData("여수 유탑 마리나\n호텔&리조트", ContextCompat.getDrawable(requireContext(),R.drawable.img_recent_one)!!,
            "4.5","4.5","(818)","50%","340,000"))

        recentList.add(LocalRecentData("여수 마린베이호텔", ContextCompat.getDrawable(requireContext(),R.drawable.img_recent_two)!!,
            "4.8","4.8","(2,251)","62%","67,200"))

        recentList.add(LocalRecentData("여수 라마다프라자 호텔", ContextCompat.getDrawable(requireContext(),R.drawable.img_recent_three)!!,
            "4.7","4.7","(633)","58%","17,600"))

        recentList.add(LocalRecentData("여수 디오션\n호텔&리조트", ContextCompat.getDrawable(requireContext(),R.drawable.img_recent_four)!!,
            "4.6","4.6","(318)","35%","141,250"))

        val linearLayoutManager = LinearLayoutManager(requireActivity())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        recentAdapter = LocalRecentAdapter(recentList,this,requireActivity())

        // 리사이클러 뷰 타입 설정
        binding.localRecentRv.layoutManager = linearLayoutManager
        // 만든 어댑터 recyclerview에 연결
        binding.localRecentRv.adapter = recentAdapter

    }
}
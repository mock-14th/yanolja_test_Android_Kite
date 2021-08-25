package com.softsquared.template.kotlin.src.main.region.ui

import RegionMainAdapter
import RegionSubAdapter
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Region
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentTabRegionBinding
import com.softsquared.template.kotlin.databinding.ItemTabRegionMainBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.region.RegionRoomListActivity
import com.softsquared.template.kotlin.src.main.region.data.RegionMainData
import com.softsquared.template.kotlin.src.main.region.data.RegionSubData
import okhttp3.internal.notifyAll
import org.w3c.dom.Text
import java.util.zip.Inflater

class RegionTabFrag : BaseFragment<FragmentTabRegionBinding>(
    FragmentTabRegionBinding::bind,
    R.layout.fragment_tab_region
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 행정구역
        val mainList = ArrayList<RegionMainData>()
        mainList.add(RegionMainData("서울"))
        mainList.add(RegionMainData("경기"))
        mainList.add(RegionMainData("인천"))
        mainList.add(RegionMainData("강원"))
        mainList.add(RegionMainData("제주"))
        mainList.add(RegionMainData("대전"))
        mainList.add(RegionMainData("충북"))
        mainList.add(RegionMainData("충남/세종"))
        mainList.add(RegionMainData("부산"))
        mainList.add(RegionMainData("울산"))
        mainList.add(RegionMainData("경남"))
        mainList.add(RegionMainData("대구"))
        mainList.add(RegionMainData("경북"))
        mainList.add(RegionMainData("광주"))
        mainList.add(RegionMainData("전남"))
        mainList.add(RegionMainData("전주/전북"))

        // 아이템 연결
        val mainAdapter = RegionMainAdapter(mainList, this, this.requireContext())
        binding.regionLvMain.adapter = mainAdapter

        // 리사이클러 뷰 타입 설정
        val linearLayoutManager = LinearLayoutManager(requireActivity())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.regionLvMain.layoutManager = linearLayoutManager

        // 구분선 삽입
        binding.regionLvMain.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        // 만든 어댑터 recyclerview에 연결
        binding.regionLvMain.adapter = mainAdapter

//        // 행정구역(서울)
        val subListSeoul = ArrayList<RegionSubData>()
        subListSeoul.add(RegionSubData("강남/역삼/삼성/논현"))
        subListSeoul.add(RegionSubData("서초/신사/방배"))
        subListSeoul.add(RegionSubData("잠실/신천(잠실새내)"))
        subListSeoul.add(RegionSubData("영등포/여의도"))
        subListSeoul.add(RegionSubData("신림/서울대/사당/동작"))
        subListSeoul.add(RegionSubData("천호/길동/둔촌"))
        subListSeoul.add(RegionSubData("화곡/까치산/양천/목동"))
        subListSeoul.add(RegionSubData("구로/금천/오류/신도림"))
        subListSeoul.add(RegionSubData("신촌/홍대/합정"))
        subListSeoul.add(RegionSubData("연신내/불광/응암"))
        subListSeoul.add(RegionSubData("종로/대학로/동묘앞역"))
        subListSeoul.add(RegionSubData("성신여대/성북/월곡"))
        subListSeoul.add(RegionSubData("이태원/용산/서울역/명동/화현"))
        subListSeoul.add(RegionSubData("동대문/을지로/충무로/신당/약수"))
        subListSeoul.add(RegionSubData("회기/고려대/청량리/신설동"))
        subListSeoul.add(RegionSubData("장안동/답십리"))
        subListSeoul.add(RegionSubData("건대/군자/구의"))
        subListSeoul.add(RegionSubData("왕십리/성수/금호"))
        subListSeoul.add(RegionSubData("수유/미아"))
        subListSeoul.add(RegionSubData("상봉/중랑/면목"))
        subListSeoul.add(RegionSubData("태릉/노원/도봉/창동"))

        // 도시 리사이클러뷰
        // 아이템 연결
        val subAdapterSeoul = RegionSubAdapter(subListSeoul, this, this.requireContext())

        // 리사이클러 뷰 타입 설정
        val linearLayoutManager2 = LinearLayoutManager(requireActivity())
        linearLayoutManager2.orientation = LinearLayoutManager.VERTICAL
        binding.regionLvSub.layoutManager = linearLayoutManager2

        // 구분선 삽입
        binding.regionLvSub.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        // 만든 어댑터 recyclerview에 연결
        binding.regionLvSub.adapter = subAdapterSeoul


        val subListGeong = ArrayList<RegionSubData>()
        subListGeong.add(RegionSubData("수원 인계/권선/영통"))
        subListGeong.add(RegionSubData("수원역/구운/장안/세류"))
        subListGeong.add(RegionSubData("안양/평촌/인덕원/과천"))
        subListGeong.add(RegionSubData("성남/분당/위례"))
        subListGeong.add(RegionSubData("용인"))
        subListGeong.add(RegionSubData("동탄/화성/오산/병점"))
        subListGeong.add(RegionSubData("하남/광주/여주/이천"))
        binding.regionLvSub.adapter = subAdapterSeoul

        val subAdapterGeong = RegionSubAdapter(subListGeong, this, this.requireContext())


        val emptyList = ArrayList<RegionSubData>()
        val emptyAdapter = RegionSubAdapter(emptyList, this, this.requireContext())

        mainAdapter.setOnItemClickListener(object : RegionMainAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: RegionMainData, pos: Int) {

                when (pos) {

                    0 -> {
                        binding.regionLvSub.adapter = subAdapterSeoul
                    }

                    1 -> {
                        binding.regionLvSub.adapter = subAdapterGeong
                    }

                    else -> binding.regionLvSub.adapter = emptyAdapter
                }

            }

        }) {
        }


        subAdapterSeoul.setOnItemClickListener(object : RegionSubAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: RegionSubData, pos: Int) {

                // 모텔 텝 화면 부르기
                val nextIntent = Intent(context, RegionRoomListActivity::class.java)

                when (pos) {
                    0 -> { // 서울/강남
                        nextIntent.putExtra("region", 1)
                        startActivity(nextIntent)
                    }

                    1 -> { // 서초/방배
                        nextIntent.putExtra("region", 3)
                        showCustomToast("regioncode:3")
                        startActivity(nextIntent)
                    }
                }
            }
        }) {}

    }
}
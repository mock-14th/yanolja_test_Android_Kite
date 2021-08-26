package com.softsquared.template.kotlin.src.main.region.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.DialogSetDayAndCntBinding
import com.softsquared.template.kotlin.databinding.FragmentRegionHotelRecoBinding
import com.softsquared.template.kotlin.databinding.FragmentRegionMotelRecoBinding
import com.softsquared.template.kotlin.src.main.region.RegionRoomListActivity
import com.softsquared.template.kotlin.src.main.region.adapter.HotelListAdapter
import com.softsquared.template.kotlin.src.main.region.adapter.MotelListAdapter
import com.softsquared.template.kotlin.src.main.region.data.HotelListData
import com.softsquared.template.kotlin.src.main.region.data.MotelListData
import com.softsquared.template.kotlin.src.main.region.models.HotelListResponse
import com.softsquared.template.kotlin.src.main.region.models.MotelListResponse
import com.softsquared.template.kotlin.src.main.region.network.HotelListFragmentView
import com.softsquared.template.kotlin.src.main.region.network.HotelListService
import com.softsquared.template.kotlin.src.main.region.network.MotelListFragmentView
import com.softsquared.template.kotlin.src.main.region.network.MotelListService

class HotelListFragment(regionCode:Int) : BaseFragment<FragmentRegionHotelRecoBinding>(
    FragmentRegionHotelRecoBinding::bind,
    R.layout.fragment_region_hotel_reco
), HotelListFragmentView {

    val TAG = "tag"

    private val hotelList = ArrayList<HotelListData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 네트워크 기본 값 연결
        showLoadingDialog(requireContext())
        HotelListService(this).tryGetHotelList(11, "2021-08-18", "2021-08-20", 1)

    }


    private fun setAdapter() {
        // 아이템 연결
        val hotelAdapter = HotelListAdapter(hotelList, this, this.requireContext())

        // 리사이클러 뷰 타입 설정
        val linearLayoutManager = LinearLayoutManager(this.requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.hotelListRv.layoutManager = linearLayoutManager

        binding.hotelListRv.adapter = hotelAdapter
    }

    override fun onGetHotelListSuccess(response: HotelListResponse) {
        dismissLoadingDialog()
        Log.d(TAG, "Get JWT 성공")

        // 성공
        if (response.code == 1000) {

            for (i in 0 until response.result.size) {

                hotelList.add(
                    HotelListData(
                        response.result[i].hotelName,
                        response.result[i].hotelImg,
                        response.result[i].hotelAvgRate,
                        response.result[i].hotelCommentCnt,
                        response.result[i].hotelSleepPrice
                    )
                )
            }

            setAdapter()

        } else { // 오류
            // 6002 지역 아이디를 입력필요
            // 6003 시작날짜 입력 필요
            // 6004 마지막날짜 입력필요
            // 6005 기준인원 입력필요
        }

    }

    override fun onGetHotelListFailure(message: String) {
        TODO("Not yet implemented")
    }

}
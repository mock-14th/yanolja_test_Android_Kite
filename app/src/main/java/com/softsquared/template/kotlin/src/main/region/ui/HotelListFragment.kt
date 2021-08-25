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
import com.softsquared.template.kotlin.src.main.region.adapter.MotelListAdapter
import com.softsquared.template.kotlin.src.main.region.data.MotelListData
import com.softsquared.template.kotlin.src.main.region.models.MotelListResponse
import com.softsquared.template.kotlin.src.main.region.network.MotelListFragmentView
import com.softsquared.template.kotlin.src.main.region.network.MotelListService

class HotelListFragment(regionCode:Int) : BaseFragment<FragmentRegionHotelRecoBinding>(
    FragmentRegionHotelRecoBinding::bind,
    R.layout.fragment_region_hotel_reco
), MotelListFragmentView {

    val TAG = "tag"

    private val motelList = ArrayList<MotelListData>()
    private lateinit var binding2: DialogSetDayAndCntBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 프래그먼트로 올릴 다이얼로그 view
        binding2 = DialogSetDayAndCntBinding.inflate(layoutInflater)


        // 네트워크 기본 값 연결
        showLoadingDialog(requireContext())
        MotelListService(this).tryGetHotelList(1, "2021-08-18", "2021-08-20", 1)

    }

    override fun onGetHotelListSuccess(response: MotelListResponse) {
        dismissLoadingDialog()
        Log.d(TAG, "Get JWT 성공")

        // 성공
        if (response.code == 1000) {

            for (i in 0 until response.result.size) {

                motelList.add(
                    MotelListData(
                        response.result[i].roomId,
                        response.result[i].hotelName,
                        response.result[i].mainPhoto,
                        response.result[i].rateAverage,
                        response.result[i].commentCnt,
                        response.result[i].rentPrice,
                        response.result[i].sleepPrcie
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

    private fun setAdapter() {
        // 아이템 연결
        val motelAdapter = MotelListAdapter(motelList, this, this.requireContext())

        // 리사이클러 뷰 타입 설정
        val linearLayoutManager = LinearLayoutManager(this.requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.motelListRv.layoutManager = linearLayoutManager

        binding.motelListRv.adapter = motelAdapter
    }
}
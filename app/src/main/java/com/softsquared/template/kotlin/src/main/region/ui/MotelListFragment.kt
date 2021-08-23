package com.softsquared.template.kotlin.src.main.region.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentRegionMotelRecoBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.login.models.PostLoginRequest
import com.softsquared.template.kotlin.src.main.login.network.LoginService
import com.softsquared.template.kotlin.src.main.region.adapter.MotelListAdapter
import com.softsquared.template.kotlin.src.main.region.data.MotelListData
import com.softsquared.template.kotlin.src.main.region.models.MotelListResponse
import com.softsquared.template.kotlin.src.main.region.network.MotelListFragmentView
import com.softsquared.template.kotlin.src.main.region.network.MotelListService

class MotelListFragment:BaseFragment<FragmentRegionMotelRecoBinding>(
    FragmentRegionMotelRecoBinding::bind,
    R.layout.fragment_region_motel_reco
),MotelListFragmentView {

    val TAG = "tag"

    private val motelList = ArrayList<MotelListData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 네트워크 기본 값 연결
        showLoadingDialog(requireContext())
        MotelListService(this).tryGetHotelList(1,"20210818","20210820",1)




        // 아이템 연결
        val motelAdapter = MotelListAdapter(motelList, this, this.requireContext())

        // 리사이클러 뷰 타입 설정
        val linearLayoutManager = LinearLayoutManager(this.requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.motelListRv.layoutManager = linearLayoutManager

        binding.motelListRv.adapter = motelAdapter

    }

    override fun onGetHotelListSuccess(response: MotelListResponse) {
        dismissLoadingDialog()
        Log.d(TAG, "Get JWT 성공")

        // 성공
        if(response.code == 1000){

            for(i in 0 until response.result.size) {
                showCustomToast(response.result[i].rateAverage)
            }
            //motelList.add(MotelListData("대치 컬리넌","","4.5","1","25,000원","50,000원"))

        }else{ // 오류
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
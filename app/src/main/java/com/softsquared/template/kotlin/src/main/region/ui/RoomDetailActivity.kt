package com.softsquared.template.kotlin.src.main.region.ui

import android.os.Bundle
import android.util.Log
import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityRoomDetailBinding
import com.softsquared.template.kotlin.src.main.region.adapter.HotelInfoViewPagerAdapter
import com.softsquared.template.kotlin.src.main.region.adapter.RoomDetailViewAdapter
import com.softsquared.template.kotlin.src.main.region.models.DetailRoomInfoResponse
import com.softsquared.template.kotlin.src.main.region.network.DetailRoomFragmentView
import com.softsquared.template.kotlin.src.main.region.network.DetailRoomService
import com.softsquared.template.kotlin.src.main.region.network.RoomInfoService

class RoomDetailActivity : BaseActivity<ActivityRoomDetailBinding>(ActivityRoomDetailBinding::inflate),DetailRoomFragmentView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val roomType = intent.getStringExtra("roomType")
        val brandId = intent.getIntExtra("brandId",1)
        val startDate = intent.getStringExtra("startDate")
        val endDate = intent.getStringExtra("endDate")

        Log.d("TAG",roomType + brandId + startDate + endDate)

        // 서버통신 api 11번 (특정 숙소의  특정 방의 상세 조회 API)
        showLoadingDialog(this)
        DetailRoomService(this).tryGetDetailRoomInfo(startDate!!, endDate!!, roomType!!, brandId)

    }

    override fun onGetDetailRoomSuccess(response: DetailRoomInfoResponse) {
        dismissLoadingDialog()
        if(response.code == 1000){

            // 대실
            binding.rentPrice.text = response.result[0].detailRent

            // 방옵션
            binding.roomDetailOptionTv.text = response.result[0].detailRoomOption

            // 방이미지


            // 방타입
            binding.roomDetailTypeTv.text = response.result[0].detailRoomType


            // 숙박가격
            binding.sleepPrice.text = response.result[0].detailSleepPrice


            // 숙소명
            binding.motelNameTv.text = response.result[0].detailRoomName

            // 예약 정보
            binding.infoOfUseTv.text = response.result[0].detailBookInfo

            // 인원
            binding.roomDetailPerCntTv.text = response.result[0].detailPersonCnt

            // 체크인
            binding.detailSleepInTime.text = response.result[0].detailCheckIn + "부터"

            // 체크아웃
            binding.detailSleepOutTimeTv.text = response.result[0].detailCheckOut + "까지"


            // 방이미지
            var roomPhotoListString = response.result[0].detailRoomImg
            var roomPhotoListArray = roomPhotoListString.split(",")

            binding.detailRoomViewpager.adapter = RoomDetailViewAdapter(this, roomPhotoListArray)

        }
    }

    override fun onGetDetailRoomFailure(message: String) {
        dismissLoadingDialog()
    }
}
package com.softsquared.template.kotlin.src.main.region.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityRoomDetailBinding
import com.softsquared.template.kotlin.src.main.book.ui.BookActivity
import com.softsquared.template.kotlin.src.main.region.adapter.HotelInfoViewPagerAdapter
import com.softsquared.template.kotlin.src.main.region.adapter.RoomDetailViewAdapter
import com.softsquared.template.kotlin.src.main.region.models.DetailRoomInfoResponse
import com.softsquared.template.kotlin.src.main.region.network.DetailRoomFragmentView
import com.softsquared.template.kotlin.src.main.region.network.DetailRoomService
import com.softsquared.template.kotlin.src.main.region.network.RoomInfoService

class RoomDetailActivity : BaseActivity<ActivityRoomDetailBinding>(ActivityRoomDetailBinding::inflate),DetailRoomFragmentView{

    // 예약 api 에 전달하기 위한 변수
    var sendStartDate ="" // startDate
    var sendEndDate = ""  // endDate
    var brandName = "" // 모텔 명
    var perCnt = "" // 기준 인원

    var forUIcheckInDate = ""
    var forUIcheckOutDate =""

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

        // 날짜 정보 받아오기
        binding.llCheckInOutDate.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivityForResult(intent, 3000)
        }

        // 예약하기 확인화면으로 넘어가기 (날짜, 숙소명, 방타입을 넘겨준다.)
        binding.bookSleepBtn.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)

            intent.putExtra("startDate",sendStartDate)
            intent.putExtra("endDate",sendEndDate)
            intent.putExtra("brandName",brandName)
            intent.putExtra("roomType",roomType)

            intent.putExtra("perCnt",perCnt)
            intent.putExtra("forUIcheckInDate",forUIcheckInDate)
            intent.putExtra("forUIcheckOutDate",forUIcheckOutDate)


            startActivity(intent)
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var date = ""
        var perCnt = ""

        if(resultCode == RESULT_OK) {
            if (requestCode == 3000) {
                forUIcheckInDate = data!!.getStringExtra("checkInDate")!!
                forUIcheckOutDate = data!!.getStringExtra("checkOutDate")!!

                sendStartDate = data!!.getStringExtra("sendStartDate")!!
                sendEndDate = data!!.getStringExtra("sendEndDate")!!

                // 적용 화면 설정
                binding.checkInTv.text = forUIcheckInDate
                binding.checkOutTv.text = forUIcheckOutDate

            } else if(requestCode == 2000){

            }


        }

    }

    override fun onGetDetailRoomSuccess(response: DetailRoomInfoResponse) {
        dismissLoadingDialog()
        if(response.code == 1000){

            // 방옵션
            binding.roomDetailOptionTv.text = response.result[0].detailRoomOption


            // 방타입
            binding.roomDetailTypeTv.text = response.result[0].detailRoomType


            // 대실, 숙박가격
            binding.detailSleepPriceTv.text = response.result[0].detailSleepPrice + "원"
            binding.detailRentPriceTv.text = response.result[0].detailRent + "원"


            // 숙소명
            binding.motelNameTv.text = response.result[0].detailRoomName
            brandName = response.result[0].detailRoomName // 넘길 데이터

            // 예약 정보
            binding.infoOfUseTv.text = response.result[0].detailBookInfo

            // 인원
            binding.roomDetailPerCntTv.text = response.result[0].detailPersonCnt
            perCnt = response.result[0].detailPersonCnt

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
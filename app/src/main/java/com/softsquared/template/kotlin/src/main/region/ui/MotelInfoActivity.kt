package com.softsquared.template.kotlin.src.main.region.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityMotelInfoBinding
import com.softsquared.template.kotlin.src.main.region.adapter.HotelInfoViewPagerAdapter
import com.softsquared.template.kotlin.src.main.region.adapter.MotelListAdapter
import com.softsquared.template.kotlin.src.main.region.adapter.MotelRoomAdapter
import com.softsquared.template.kotlin.src.main.region.data.MotelListData
import com.softsquared.template.kotlin.src.main.region.data.MotelRoomData
import com.softsquared.template.kotlin.src.main.region.models.RoomInfoResponse
import com.softsquared.template.kotlin.src.main.region.network.RoomInfoFragmentView
import com.softsquared.template.kotlin.src.main.region.network.RoomInfoService

class MotelInfoActivity : BaseActivity<ActivityMotelInfoBinding>(ActivityMotelInfoBinding::inflate),
    RoomInfoFragmentView {

    var count: Int = 0
    val TAG = "TAG"
    private var motelRoomList = ArrayList<MotelRoomData>()

    var this_branId = 1
    var this_startDate = ""
    var this_endDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // 이전 fragment에서 선택한 방 정보 받아오기
        val brandID = intent.getIntExtra("brandID", 1)
        val startDate = intent.getStringExtra("startDate").toString()
        val endDate = intent.getStringArrayExtra("endDate").toString()


        // DetailRoomActivity에 넘겨줄 데이터 변수 담기
        this_branId = brandID
        this_startDate = startDate
        this_endDate = endDate


        // 서버통신 api 10번 (특정 숙소의 날짜에 따른 방목록 조회)
        showLoadingDialog(this)
        RoomInfoService(this).tryGetRoomInfoList(brandID,startDate,endDate)


        // 객실 선택 리스너 -> 객실 recyclerview 로 스크롤 이동
        binding.moveToSelectRoomBtn.setOnClickListener {
            scrollToView(binding.thisRoomListRv, binding.nestedScrollview, 0)
        }

        // 찜 toast 메세지 띄우기
        binding.buttonFavorite.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                // 인증번호 토스트 메세지
                Toast(this).showCustomToast(
                    this
                )
            }
        }

    }


    // 스크롤 이동 이벤트
    private fun scrollToView(view: View, scrollView: NestedScrollView, count: Int) {

        if (view != null && view != scrollView) {
            this.count += view.top
            scrollToView((view.parent as View), scrollView, count)
        } else if (scrollView != null) {
            val finalCount = count
            scrollView.smoothScrollTo(0, finalCount)
        }
    }

    // 찜 토스트 메세지
    fun Toast.showCustomToast(activity: Activity) {
        val layout = activity.layoutInflater.inflate(
            R.layout.dialog_like_toast,
            activity.findViewById(R.id.toast_like_container)
        )

        // use the application extension function
        this.apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }
    }

    override fun onGetRoomInfoSuccess(response: RoomInfoResponse) {
        dismissLoadingDialog()
        if (response.code == 1000) {

            // 모텔의 기본 정보 받아오기 (BrandInfo)
            // 숙박업소명
            binding.hotelNameTvTopbar.text = response.result.brandInfo[0].hotelName
            binding.hotelNameTv.text = response.result.brandInfo[0].hotelName

            // 후기개수
            binding.commentCntTv.text = response.result.brandInfo[0].commentCnt.toString()

            // 주소
            binding.locationTv.text = response.result.brandInfo[0].address

            // 이용안내
            binding.useInfoTv.text = response.result.brandInfo[0].motelHowToUse

            // 평균별점
            binding.starRateTv.text = response.result.brandInfo[0].avgStartRate
            binding.startRateAvgBottomTv.text = response.result.brandInfo[0].avgStartRate

            // 청결도
            binding.cleanTv.text = response.result.brandInfo[0].clean
            binding.cleanProgress.progress = response.result.brandInfo[0].clean.toString().toInt()

            // 비품만족도
            binding.satisfyTv.text = response.result.brandInfo[0].goodsSatify
            binding.satisfyProgress.progress =
                response.result.brandInfo[0].goodsSatify.toString().toInt()

            // 친절도
            binding.kindTv.text = response.result.brandInfo[0].kind
            binding.kindProgress.progress = response.result.brandInfo[0].kind.toString().toInt()

            // 편의성
            binding.easeTv.text = response.result.brandInfo[0].ease
            binding.easeProgress.progress = response.result.brandInfo[0].ease.toString().toInt()

            // 대표이미지 설정
            // viewpager
            var roomPhotoListString = response.result.brandInfo[0].motelImg
            var roomPhotoListArray = roomPhotoListString.split(",")

            binding.roomViewpager.adapter = HotelInfoViewPagerAdapter(this, roomPhotoListArray)


            // 존재하는 방 정보 받아오기 (Room)
            val roomCnt = response.result.roomList.size

            for (i in 0 until roomCnt) {

                motelRoomList.add(
                    MotelRoomData(
                        response.result.roomList[i].roomImg,
                        response.result.roomList[i].roomType,
                        response.result.roomList[i].roomOption,
                        response.result.roomList[i].roomGizoonCnt,
                        response.result.roomList[i].roomMaxCnt,
                        response.result.roomList[i].roomRentPrice,
                        response.result.roomList[i].roomSleepPrice,
                        response.result.roomList[i].roomCheckInTime,
                        this_branId,
                        this_startDate,
                        this_endDate
                    )
                )
            }

            setMotelRoomListAdapter()


            if (this_branId == 1) {

                if (response.result.reviewList[0] != null) {
                    // 이용후기 (Review)
                    // (1) 닉네임
                    binding.reviewNicknameTv1.text = response.result.reviewList[0].reviewNickName + " | "
                    // 별점

                    // 사용한 방
                    binding.reviewUsedRoomTv1.text = response.result.reviewList[0].reviewUsedRoom

                    // 사용한 방의 옵션
                    binding.reviewRoomOption1.text = response.result.reviewList[0].reviewRoomOption

                    // 후기 쓴 날짜
                    binding.reviewWroteDate1.text = response.result.reviewList[0].reviewWroteDay

                    // 내용
                    binding.reviewReviewComment1.text = response.result.reviewList[0].reviewContent

                    // 후기 이미지
                    if (response.result.reviewList[0].reviewImg != null) {
                        Glide.with(this).load(response.result.reviewList[0].reviewImg)
                            .into(binding.reviewImgOne1)
                        binding.reviewImgOne2.visibility = View.GONE
                    } else {
                        binding.reviewImgOne1.visibility = View.GONE
                        binding.reviewImgOne2.visibility = View.GONE
                    }

                    // (2) 닉네임
                    binding.reviewNicknameTv2.text = response.result.reviewList[1].reviewNickName +" | "
                    // 별점

                    // 사용한 방
                    binding.reviewUsedRoomTv2.text = response.result.reviewList[1].reviewUsedRoom

                    // 사용한 방의 옵션
                    binding.reviewRoomOption2.text = response.result.reviewList[1].reviewRoomOption

                    // 후기 쓴 날짜
                    binding.reviewWroteDate2.text = response.result.reviewList[1].reviewWroteDay

                    // 내용
                    binding.reviewReviewComment2.text = response.result.reviewList[1].reviewContent

                    // 후기 이미지
                    if (response.result.reviewList[1].reviewImg != null) {
                        Glide.with(this).load(response.result.reviewList[1].reviewImg)
                            .into(binding.reviewImgTwo1)
                        binding.reviewImgTwo2.visibility = View.GONE
                    } else {
                        binding.reviewImgTwo1.visibility = View.GONE
                        binding.reviewImgTwo2.visibility = View.GONE
                    }


                }
            }
        }


    }

    override fun onGetRoomInfoFailure(message: String) {
        dismissLoadingDialog()
        Log.e(TAG, message)
    }

    private fun setMotelRoomListAdapter() {

        val motelRoomListAdapter = MotelRoomAdapter(this, motelRoomList)

        // 리사이클러 뷰 타입 설정
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.thisRoomListRv.layoutManager = linearLayoutManager

        binding.thisRoomListRv.adapter = motelRoomListAdapter

    }
}
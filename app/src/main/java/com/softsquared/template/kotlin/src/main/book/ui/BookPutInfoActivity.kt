package com.softsquared.template.kotlin.src.main.book.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.myBookedNum
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityBookPutInfoBinding
import com.softsquared.template.kotlin.src.main.book.models.GetUsersPhoneResponse
import com.softsquared.template.kotlin.src.main.book.models.NightBookingResponse
import com.softsquared.template.kotlin.src.main.book.models.PostNightBookingRequest
import com.softsquared.template.kotlin.src.main.book.network.GetUserPhoneNumService
import com.softsquared.template.kotlin.src.main.book.network.NightBookingFragmentView
import com.softsquared.template.kotlin.src.main.book.network.PostNightBookingService
import com.softsquared.template.kotlin.src.main.book.network.UserPhoneNumFragmentView
import com.softsquared.template.kotlin.src.main.myPage.network.UserInfoService

class BookPutInfoActivity :
    BaseActivity<ActivityBookPutInfoBinding>(ActivityBookPutInfoBinding::inflate),
    UserPhoneNumFragmentView, NightBookingFragmentView {

    // 이전 클래스에서 받아오기
    private lateinit var startDate: String
    private lateinit var endDate: String
    private lateinit var brandName: String
    private lateinit var roomType: String

    // ui 에서 받아오기
    private lateinit var memName: String
    private lateinit var userName: String
    private lateinit var phoneNum: String

    // 기본 설정
    private var isWalk = "도보"
    private var payKind = "카카오페이"

    val TAG ="tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        startDate = intent.getStringExtra("startDate")!!
//        endDate = intent.getStringExtra("endDate")!!
        startDate = "2021-08-25"
        endDate ="2021-08-25"
//        brandName = intent.getStringExtra("brandName")!!
//        roomType = intent.getStringExtra("roomType")!!
        brandName = "역삼 카파스"
        roomType = "Standard"




        // 체크 버튼 누르면 동일한 정보로 취급
        binding.sameAsBookerCheckbox.setOnClickListener {
            binding.userNameTv.setText(binding.memNameTv.text)
            binding.samePhoneTv.setText(binding.usersPhoneNumTv.text)
        }

        binding.goToPayBtn.setOnClickListener {
            memName = binding.memNameTv.text.toString()
            userName = binding.userNameTv.text.toString()

            Log.d(TAG,"startDate:$startDate, endDate:$endDate, bradName:$brandName, roomType:$roomType" + isWalk + userName + phoneNum + payKind + memName)


            val apiStartDate = startDate.substring(0,4) + startDate.substring(5,7) + startDate.substring(7,8)

            val apiEndDate = endDate.substring(0,4) + "-" + endDate.substring(5,6) + "-" +startDate.substring(7,8)

            //showCustomToast(apiStartDate + apiEndDate)

            //2021(-)08(-)29

            //Log.d(TAG, apiStartDate + apiEndDate)


            val postRequest = PostNightBookingRequest(
                brandName="역삼 컬리넌",
                endDate="2016-08-23",
                isWalk=isWalk,
                memName=memName,
                payKind=payKind,
                roomType="Standard",
                startDate="2016-08-18",
                userName=userName,
                userPhone= phoneNum
            )

            // 예약하기 서버통신 (api 23번)
            showLoadingDialog(this)
            PostNightBookingService(this).tryPostNightBooking(postRequest)
        }

        // 사용자 핸드폰 번호 받아오기 api 18번
        showLoadingDialog(this)
        GetUserPhoneNumService(this).tryGetUserPhoneNum()
    }

    override fun onGetPhoneNumSuccess(response: GetUsersPhoneResponse) {
        dismissLoadingDialog()
        binding.usersPhoneNumTv.text = response.result[0].usersPhoneNum
        phoneNum = response.result[0].usersPhoneNum

    }

    override fun onGetPhoneNumFailure(message: String) {
        dismissLoadingDialog()
        Log.e("TAG", message)
    }


    override fun onPostBookingSuccess(response: NightBookingResponse) {
        dismissLoadingDialog()

        if (response.code == 1000) {
            // 예약하고 결제창으로 넘어가기
            val nextIntent = Intent(this, BookCompleteActivity::class.java)


            // 예약번호 넘겨 받기
            val bookedNum = response.result[0].bookedNum
            nextIntent.putExtra("bookedNum",bookedNum)

            // 예약번호 저장하기
            myBookedNum = bookedNum

            Log.d(TAG,"BOOKNUMED:"+ bookedNum.toString())


            startActivity(nextIntent)
        } else {
            showCustomToast(response.code.toString())
        }
    }

    override fun onPostBookingFailure(message: String) {
        dismissLoadingDialog()
        Log.e("TAG", message)
    }
}
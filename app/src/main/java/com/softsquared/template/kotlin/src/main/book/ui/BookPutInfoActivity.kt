package com.softsquared.template.kotlin.src.main.book.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 사용자 핸드폰 번호 받아오기 api 18번
        showLoadingDialog(this)
        GetUserPhoneNumService(this).tryGetUserPhoneNum()

        startDate = intent.getStringExtra("startDate")!!
        endDate = intent.getStringExtra("endDate")!!
        brandName = intent.getStringExtra("brandName")!!
        roomType = intent.getStringExtra("roomType")!!

        // 체크 버튼 누르면 동일한 정보로 취급
        binding.sameAsBookerCheckbox.setOnClickListener {
            binding.userNameTv.setText(binding.memNameTv.text)
        }

        binding.goToPayBtn.setOnClickListener {
            memName = binding.memNameTv.text.toString()
            userName = binding.userNameTv.text.toString()


            val postRequest = PostNightBookingRequest(
                brandName=brandName,
                endDate=endDate,
                isWalk=isWalk,
                memName=memName,
                payKind=payKind,
                roomType=roomType,
                startDate=startDate,
                userName=userName,
                userPhone= phoneNum
            )

            // 예약하기 서버통신 (api 23번)
            showLoadingDialog(this)
            PostNightBookingService(this).tryPostNightBooking(postRequest)
        }
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
            showCustomToast("예약 성공")
            // 예약하고 결제창으로 넘어가기
            val nextIntent = Intent(this, BookPutInfoActivity::class.java)
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
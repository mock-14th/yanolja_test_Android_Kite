package com.softsquared.template.kotlin.src.main.book.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.myBookedNum
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityBookCompleteBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.book.models.PostPayRequest
import com.softsquared.template.kotlin.src.main.book.models.PostPayResponse
import com.softsquared.template.kotlin.src.main.book.network.GetUserPhoneNumService
import com.softsquared.template.kotlin.src.main.book.network.PayFragmentView
import com.softsquared.template.kotlin.src.main.book.network.PostPayService

class BookCompleteActivity:BaseActivity<ActivityBookCompleteBinding>(ActivityBookCompleteBinding::inflate) , PayFragmentView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 결제 요청 API (24번)
        showLoadingDialog(this)
        //val postPayRequest = PostPayRequest(myBookedNum,0)
        PostPayService(this).tryPostPay(myBookedNum,0)

        binding.goToHomeBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onPostPaySuccess(response: PostPayResponse) {
        dismissLoadingDialog()

        if(response.code == 1000){

            // 상품 및 이용정보 payGoodsAndUseInfo
            //썸네일
            Glide.with(this).load(response.result[0].payGoodsAndUseInfo.roomSumbnail).into(binding.roomIv)
            //브랜드이름
            binding.roomName.text = response.result[0].payGoodsAndUseInfo.brandName
            //방타입
            binding.roomTypeTv.text = response.result[0].payGoodsAndUseInfo.roomType
            //방옵션
            binding.roomOptionTv.text = response.result[0].payGoodsAndUseInfo.roomOption
            //이용날짜  //예약요일
            binding.bookTimeTv.text = response.result[0].payGoodsAndUseInfo.bookdedTime + response.result[0].payGoodsAndUseInfo.bookedDate

            //체크인아웃
            val checkinoutArray = response.result[0].payGoodsAndUseInfo.checkInAndOut.split(",")
            binding.checkInAndOutTv.text = checkinoutArray[0] + " | " + checkinoutArray[1]




            //  "이용자정보": "이름: ddd, 휴대폰 번호: 01011111111"
            val bookerInfoArray = response.result[0].payBookerInfo.userInfo.split(",")

            val bookerName = bookerInfoArray[0].replace("이름: ","")
            val bookerPhone = bookerInfoArray[1].replace("휴대폰 번호: ","")
            // 이름: ddd
            // 휴대폰 번호: 0101111111

            // 예약자 정보 payBookerInfo
            binding.bookerNameTv.text = bookerName
            binding.bookerPhoneNumTv.text = bookerPhone

            // 이용자 정보 payUserInfo
            // 이름, 휴대폰 번호
            val userInfoArray = response.result[0].payUserInfo.userInfo.split(",")

            val userName = userInfoArray[0].replace("이름: ","")
            val userPhone = userInfoArray[1].replace("휴대폰 번호: ","")

            binding.userNameTv.text = userName
            binding.userPhoneNumTv.text = userPhone


            // 금액 및 할인 정보 payPriceAndDiscountInfo

            // 주문번호
            binding.bookNumTv.text = response.result[0].payPriceAndDiscountInfo.bookedNum.toString()
            // 예약상품
            binding.bookGoodsTv.text = response.result[0].payPriceAndDiscountInfo.bookGoods
            // 결제 금액
            binding.payPriceTv.text = response.result[0].payPriceAndDiscountInfo.payPrice + "원"

        }
    }

    override fun onPostPayFailure(message: String) {
        dismissLoadingDialog()
        Log.e("TAG", message)
    }
}
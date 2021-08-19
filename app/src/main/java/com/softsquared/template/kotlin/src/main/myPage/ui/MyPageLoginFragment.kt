package com.softsquared.template.kotlin.src.main.myPage.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ScrollView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentMyPageLoginBinding
import com.softsquared.template.kotlin.src.main.login.ui.LoginSettingActivity
import com.softsquared.template.kotlin.src.main.myPage.models.UserInfoResponse
import com.softsquared.template.kotlin.src.main.myPage.network.MyPageFragmentView
import com.softsquared.template.kotlin.src.main.myPage.network.UserInfoService

class MyPageLoginFragment :
    BaseFragment<FragmentMyPageLoginBinding>(FragmentMyPageLoginBinding::bind, R.layout.fragment_my_page_login),MyPageFragmentView {

    val TAG = "tag"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 회원 정보 받아오기
        getUserInfo()

        // floating 버튼 안 보이게 하기
        binding.myPageFloatBtnGoToTop.hide()

        // '맨위로' 버튼과 상단바 '그림자' 를 조절하기 위한 리스너
        binding.myPageScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // 최상단 뷰에 스크롤이 도달해 있다면
            if(binding.myPageScrollView.scrollY == 0) {
                binding.myPageAbLayout.elevation = 0F // 그림자 없애기
                binding.myPageFloatBtnGoToTop.hide() // float 버튼 없애기
            }else{
                binding.myPageAbLayout.elevation = 8F // 그림자 넣기
                binding.myPageFloatBtnGoToTop.show() // float 버튼 보이기
            }
        }

        // '맨위로' floating btn 리스너
        binding.myPageFloatBtnGoToTop.setOnClickListener {
            // 스크롤뷰 최상단으로 고정하기
            binding.myPageScrollView.fullScroll(ScrollView.FOCUS_UP)
        }

        // 설정 창으로 이동
        binding.settingLayout.setOnClickListener {
            // 설정 화면으로 이동
            val intent = Intent(context, LoginSettingActivity::class.java)
            startActivity(intent)
        }



    }

    override fun onGetUserInfoSuccess(response: UserInfoResponse) {
        dismissLoadingDialog()

        // 정보 조회 성공
        if(response.code == 1000){

            // 닉네임
            binding.myPageTvNickname.text = response.result.nickname

            // 포인트
            binding.pointTv.text = response.result.point.toString()

            // 쿠폰함
            binding.couponTv.text = response.result.coupon.toString()

            // 장바구니
            binding.myPageTvShopBadge.text = response.result.basket.toString()

        }else{
            Log.e(TAG,response.code.toString())
        }
    }

    // 정보조회 실패
    override fun onGetUserInfoFailure(message: String) {
        dismissLoadingDialog()
        Log.e("TAG", message)
    }

    // 회원 정보 조회 api
    private fun getUserInfo(){
        showLoadingDialog(requireContext())
        UserInfoService(this).tryGetUserInfo()
    }

}
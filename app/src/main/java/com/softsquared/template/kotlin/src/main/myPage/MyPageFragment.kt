package com.softsquared.template.kotlin.src.main.myPage

import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentMyPageBinding
import com.softsquared.template.kotlin.src.main.login.LoginActivity
import com.softsquared.template.kotlin.src.main.login.LoginSettingActivity

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // floating 버튼 안 보이게 하기
        binding.myPageFloatBtnGoToTop.hide()

        // 로그인 화면 이동 (1)
        binding.cvColOne.setOnClickListener {
            // 로그인 화면으로 이동
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        // 로그인 화면 이동 (2) - MY 혜택
        binding.myPageBtnMyProfit.setOnClickListener {
            // 로그인 화면으로 이동
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }


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
}
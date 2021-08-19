package com.softsquared.template.kotlin.src.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.loginState
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.sSharedPreferences
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.like.LikeFragment
import com.softsquared.template.kotlin.src.main.myNear.MyNearFragment
import com.softsquared.template.kotlin.src.main.myPage.ui.MyPageFragment
import com.softsquared.template.kotlin.src.main.myPage.ui.MyPageLoginFragment
import com.softsquared.template.kotlin.src.main.region.RegionFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 로그인 상태 불러오기
        loginState = sSharedPreferences.getInt("saveLogin", 0)

        // 로그인 or 회원가입 화면에서 넘어올 때 => MY 야놀자
        if(intent.hasExtra("afterLoginTask")){

            // 바텀 네비 focus 화면 설정
            binding.mainBtmNav.selectedItemId = R.id.menu_main_btm_nav_my_page

            val loginFlag = intent.getBooleanExtra("afterLoginTask",false)

            if(loginFlag == false) { // 로그아웃 MY 야놀자
                supportFragmentManager.beginTransaction().replace(R.id.main_frm, MyPageFragment())
                    .commitAllowingStateLoss()
            }else{ // 로그인 MY 야놀자
                supportFragmentManager.beginTransaction().replace(R.id.main_frm, MyPageLoginFragment())
                    .commitAllowingStateLoss()
            }
        }else{
            // 앱 실행 직후의 화면 => 홈화면
            supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()
        }

        binding.mainBtmNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    // 홈
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }

                    // 지역
                    R.id.menu_main_btm_nav_region -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, RegionFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }

                    // 내주변
                    R.id.menu_main_btm_nav_my_near -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MyNearFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }

                    // 찜
                    R.id.menu_main_btm_nav_like -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, LikeFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }

                    // MY 야놀자
                    R.id.menu_main_btm_nav_my_page -> {
                        when(loginState) {
                            0 -> { // 로그아웃
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.main_frm, MyPageFragment())
                                    .commitAllowingStateLoss()
                                return@OnNavigationItemSelectedListener true
                            }
                            1 -> { // 로그인
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.main_frm, MyPageLoginFragment())
                                    .commitAllowingStateLoss()
                                return@OnNavigationItemSelectedListener true
                            }
                        }
                    }
                }
                false
            })
    }
}
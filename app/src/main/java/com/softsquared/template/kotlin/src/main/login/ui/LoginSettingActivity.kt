package com.softsquared.template.kotlin.src.main.login.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityLoginSettingBinding
import com.softsquared.template.kotlin.src.main.login.dialog.LogoutDialog

class LoginSettingActivity : BaseActivity<ActivityLoginSettingBinding>(ActivityLoginSettingBinding::inflate) {

    private val prefEdit = ApplicationClass.sSharedPreferences.edit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뒤로가기 버튼
        binding.settingLoginIbtnBack.setOnClickListener {
            finish()
        }

        // 로그아웃 팝업
        binding.settingLogoutArea.setOnClickListener {
            val dialog = LogoutDialog()
            dialog.show(supportFragmentManager, "LogoutDialog")
        }

    }

}

package com.softsquared.template.kotlin.src.main.login.dialog

import android.content.Intent
import android.content.Intent.*
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.loginState
import com.softsquared.template.kotlin.databinding.DialogLogoutBinding
import com.softsquared.template.kotlin.src.main.MainActivity

class LogoutDialog : DialogFragment() {
    private val prefEdit = ApplicationClass.sSharedPreferences.edit()
    private var _binding: DialogLogoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogLogoutBinding.inflate(inflater, container, false)
        val view = binding.root

        // 레이아웃 배경을 투명하게 해줌
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 취소
        binding.logoutBtnCancel.setOnClickListener {
            dismiss()
        }

        // 확인
        binding.logoutBtnConfirm.setOnClickListener {

            // 로그아웃
            loginState = 0
            prefEdit.putInt("saveLogin",loginState).apply()

            // jwt 초기화
            prefEdit.putString(X_ACCESS_TOKEN,null).apply()

            // 메인화면으로 이동(MY 야놀자)
            val mainIntent = Intent(context,MainActivity::class.java)

            // 쌓여있는 모든 액티비티 지우기
            mainIntent.setFlags(FLAG_ACTIVITY_NEW_TASK and FLAG_ACTIVITY_CLEAR_TASK and FLAG_ACTIVITY_CLEAR_TOP)

            // MY 야놀자 페이지로 바로 이동하게 하기 위한 코드
            mainIntent.putExtra("afterLoginTask",false)

            // 메인화면으로 이동
            startActivity(mainIntent)


        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.softsquared.template.kotlin.src.main.login

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentLoginWithYanolzaBinding

class DefaultLoginTab:
    BaseFragment<FragmentLoginWithYanolzaBinding>(FragmentLoginWithYanolzaBinding::bind, R.layout.fragment_login_with_yanolza) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginLoginBtn.isEnabled = false // 로그인 버튼 비활성화

        // 로그인 버튼 활성화(id 영역)
        binding.idInputET.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.idInputET.length() >= 1 && binding.pwInputET.length() >= 1) {  // 버튼 클릭할수 있게
                    binding.loginLoginBtn.isEnabled = true // 로그인 버튼 활성화
                    binding.loginLoginBtn.setBackgroundResource(R.drawable.btn_login_active)
                }else{
                    binding.loginLoginBtn.isEnabled = false // 로그인 버튼 활성화
                    binding.loginLoginBtn.setBackgroundResource(R.drawable.btn_login_login)
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        // 로그인 버튼 활성화(비밀번호 영역)
        binding.pwInputET.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
              if(binding.idInputET.length() >= 1 && binding.pwInputET.length() >= 1) {  // 버튼 클릭할수 있게
                  binding.loginLoginBtn.isEnabled = true // 로그인 버튼 활성화
                  binding.loginLoginBtn.setBackgroundResource(R.drawable.btn_login_active)
              }else{
                  binding.loginLoginBtn.isEnabled = false // 로그인 버튼 활성화
                  binding.loginLoginBtn.setBackgroundResource(R.drawable.btn_login_login)
              }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

    }
}
package com.softsquared.template.kotlin.src.main.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.loginState
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.sSharedPreferences
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentLoginWithYanolzaBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.HomeService
import com.softsquared.template.kotlin.src.main.home.models.UserResponse
import com.softsquared.template.kotlin.src.main.login.models.LoginResponse
import com.softsquared.template.kotlin.src.main.login.models.PostLoginRequest
import com.softsquared.template.kotlin.src.main.register.RegisterActivity
import kotlin.math.log

class DefaultLoginTab:
    BaseFragment<FragmentLoginWithYanolzaBinding>(FragmentLoginWithYanolzaBinding::bind, R.layout.fragment_login_with_yanolza),LoginFragmentView {

    private val prefEdit = sSharedPreferences.edit()

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

        // 회원가입 화면 이동
        binding.loginRegisterBtn.setOnClickListener {
            val registerIntent = Intent(context, RegisterActivity::class.java)
            startActivity(registerIntent)

        }

        // 로그인하기 - (서버 통신)
        binding.loginLoginBtn.setOnClickListener {
            val id = binding.idInputET.text.toString()
            val password = binding.pwInputET.text.toString()
            val postRequest = PostLoginRequest(email=id,password=password)
            showLoadingDialog(requireContext())
            LoginService(this).tryPostLogin(postRequest)
        }

    }

    // 로그인 요청 성공
    override fun onPostLoginSuccess(response: LoginResponse) {
        dismissLoadingDialog()
        showCustomToast("Get JWT 성공")

        // 성공
        if(response.code == 1000){

            // jwt 토큰 저장하기
            prefEdit.putString(X_ACCESS_TOKEN,response.result.jwt)

            // 로그(인)으로 상태변경
            loginState = 1

            // 로그인 상태 저장(for 자동 로그인)
            prefEdit.putInt("saveLogin", loginState).apply()

            // 메인화면으로 이동
            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
        }else{ // 오류
            binding.pwTextLayout.error = "이메일(ID 또는 비밀번호를 확인 후 다시 로그인해주세요.(5회이상 오류시 로그인 차단)"
        }

    }

    // 로그인 실패
    override fun onPostLoginFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }


}
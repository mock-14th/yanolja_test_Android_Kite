package com.softsquared.template.kotlin.src.main.login.register

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Toast
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.databinding.ActivityRegister2Binding

class RegisterActivity2 : BaseActivity<ActivityRegister2Binding>(ActivityRegister2Binding::inflate)  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 확인 버튼 비활성화
        binding.registerConfirmBtn.isClickable = false

        if (intent.hasExtra("userEmail")) {
            // 이전 Activity 에서 입력받은 email 정보 넣기
            val email = intent.getStringExtra("userEmail")
            binding.emailInputET.setText(email)
        }


        // 비밀번호 에러 감지 ???? check again
        binding.pwInputET.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.pwInputET.text?.length!! in 4..8){ // 8자리 이하로 입력
                    binding.pwTextLayout.error = getString(R.string.pw_helper_error) //에러 메세지
                }
                else{
                    binding.pwTextLayout.error = null
                }
            }
            override fun afterTextChanged(s: Editable?) { // 조건 미충족

            }
        })

        // 비밀번호 일치 여부 확인
        binding.pwConfirmInputET.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.pwConfirmInputET.text.toString() == binding.pwInputET.text.toString()){ // 일치할 때
                    binding.pwConfirmTextLayout.error = null // 에러 메세지 비활성화
                    binding.registerConfirmBtn.isClickable = true // 버튼 활성화
                    binding.registerConfirmBtn.setBackgroundResource(R.drawable.btn_login_active)
                    binding.registerConfirmBtn.setTextColor(Color.WHITE)
                }else{ // 일치하지 않을 때
                    binding.pwConfirmTextLayout.error = getString(R.string.pw_confirm_helper_error) // 에러 메세지 뜨게 하기
                    binding.registerConfirmBtn.isClickable = true // 버튼 비활성화
                    binding.registerConfirmBtn.setBackgroundResource(R.drawable.bg_btn_next)
                    binding.registerConfirmBtn.setTextColor(resources.getColor(R.color.secondColor))
                }
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })

        // 정보 동의 팝업 창
        binding.registerConfirmBtn.setOnClickListener {
            val bottomSheet = FragmentAgreeBtm()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }
}
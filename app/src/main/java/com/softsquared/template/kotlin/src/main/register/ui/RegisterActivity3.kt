package com.softsquared.template.kotlin.src.main.register.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityRegister3Binding

class RegisterActivity3 : BaseActivity<ActivityRegister3Binding>(ActivityRegister3Binding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 버튼 비활성화
        binding.phoneCertificationBtn.isClickable = false

        binding.phoneInputET.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.phoneInputET.text?.length!! >= 13){ // 휴대폰 번호 입력 완료
                    binding.phoneCertificationBtn.isClickable = true // 버튼 활성화
                    binding.phoneCertificationBtn.setBackgroundResource(R.drawable.bg_blue_stroke_btn)
                    binding.phoneCertificationBtn.setTextColor(resources.getColor(R.color.myPageBlue))
                }
                else{
                    // 버튼 비활성화
                    binding.phoneCertificationBtn.isClickable = false
                    binding.phoneCertificationBtn.setBackgroundResource(R.drawable.bg_btn_next)
                    binding.phoneCertificationBtn.setTextColor(resources.getColor(R.color.LoginBtnTxt))
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.phoneCertificationBtn.setOnClickListener {

            if(intent.hasExtra("email") && intent.hasExtra("pw")) {

                val email = intent.getStringExtra("email")
                val pw = intent.getStringExtra("pw")

                val nextIntent = Intent(this, RegisterLastActivity::class.java)
                nextIntent.putExtra("phone", binding.phoneInputET.text.toString())
                nextIntent.putExtra("email",email)
                nextIntent.putExtra("pw",pw)
                startActivity(nextIntent)
                finish()
            }
        }

    }
}
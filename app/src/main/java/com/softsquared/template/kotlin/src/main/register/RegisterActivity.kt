package com.softsquared.template.kotlin.src.main.register

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityRegisterBinding

class RegisterActivity  : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //LoadingScreen.displayLoadingWithText(this, false)
        binding.registerConfirmBtn.isClickable = false// 다음 버튼 비활성화

        // 다음 입력창으로 화면 전환
        binding.registerConfirmBtn.setOnClickListener {
            LoadingScreen.displayLoadingWithText(this, false)
            val nextIntent = Intent(this, RegisterActivity2::class.java)

            // 입력한 이메일 정보 다음 Activity 에 넘겨주기
            nextIntent.putExtra("userEmail",binding.emailInputET.text.toString())
            startActivity(nextIntent)
            finish()
        }

        // 다음 버튼 활성화
        binding.emailInputET.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.emailInputET.text.toString().contains(".") && binding.emailInputET.length() >= 1) {  // 버튼 클릭할수 있게
                    binding.registerConfirmBtn.isClickable = true
                    binding.registerConfirmBtn.setBackgroundResource(R.drawable.btn_login_active)
                    binding.registerConfirmBtn.setTextColor(Color.WHITE)
                }else{
                    binding.registerConfirmBtn.isClickable = false
                    binding.registerConfirmBtn.setBackgroundResource(R.drawable.bg_btn_next)
                    binding.registerConfirmBtn.setTextColor(resources.getColor(R.color.secondColor))
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }


    // 로딩화면
    object LoadingScreen {
        var dialog: Dialog? = null //obj

        fun displayLoadingWithText(
            context: Context?,
            cancelable: Boolean
        ) { // function -- context(parent (reference))
            dialog = Dialog(context!!)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setContentView(R.layout.dialog_loading_screen)
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.setCancelable(cancelable)
            try {
                dialog!!.show()
            } catch (e: Exception) {
            }
        }

        fun hideLoading() {
            try {
                if (dialog != null) {
                    dialog!!.dismiss()
                }
            } catch (e: Exception) {
            }
        }

    }
}
package com.softsquared.template.kotlin.src.main.register

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityRegisterLastBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.register.dialog.DialogPhoneNum
import kotlin.math.roundToInt

class RegisterLastActivity : BaseActivity<ActivityRegisterLastBinding>(ActivityRegisterLastBinding::inflate) {

    private lateinit var mHandler: Handler
    private lateinit var mRunnable:Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 회원가입 버튼 비활성화
        binding.registerConfirmBtn.isClickable = false

        // 인증번호 토스트 메세지
        Toast(this).showCustomToast (
            "인증번호가 발송되었습니다.",
            this
        )

        // 제한시간 시작
        mCountDown.start()

        if (intent.hasExtra("phoneNum")) {
            // 이전 Activity 에서 입력받은 phoneNum 정보 넣기
            val phoneNum = intent.getStringExtra("phoneNum")
            binding.phoneInputET.setText(phoneNum)
        }

        binding.phoneSixInputET.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.phoneSixInputET.text?.length!! >= 6){ // 인증번호 입력 완료
                    binding.registerConfirmBtn.isClickable = true // 버튼 활성화
                    binding.registerConfirmBtn.setBackgroundResource(R.drawable.btn_login_active)
                    binding.registerConfirmBtn.setTextColor(Color.WHITE)
                }
                else{
                    // 버튼 비활성화
                    binding.registerConfirmBtn.isClickable = false
                    binding.registerConfirmBtn.setBackgroundResource(R.drawable.bg_btn_next)
                    binding.registerConfirmBtn.setTextColor(resources.getColor(R.color.LoginBtnTxt))
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })


        // 여기서 ~~~~~~~~~~~~~~~~~~~~~~~~~ 회원가입 api를 연결하자~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // My 야놀자 화면으로 이동(Fragment 띄우기)
        binding.registerConfirmBtn.setOnClickListener {
            val myPageIntent = Intent(this,MainActivity::class.java)
            startActivity(myPageIntent)
        }




    }


    private val mCountDown: CountDownTimer = object : CountDownTimer(90000, 500) {
        override fun onTick(millisUntilFinished: Long) {
            //update the UI with the new count

            if((millisUntilFinished.toFloat() / 1000.0f).roundToInt() < 60){
                binding.limitTimeTv.text =("${(millisUntilFinished.toFloat() / 1000.0f).roundToInt()}초")
            }else{
                binding.limitTimeTv.text =("1분 ${(((millisUntilFinished-60000).toFloat())/1000.0f).roundToInt()}초")
            }

        }

        override fun onFinish() {
            //countdown finish
            //onClickStart()
        }
    }

    fun showDialog() {
        mRunnable = Runnable {
            // 인증번호 전송 팝업창 띄우기
            val dialog = DialogPhoneNum()
            dialog.show(supportFragmentManager, "DialogPhoneNum")

            // Schedule the task to repeat after 1 second
            mHandler.postDelayed(
                mRunnable, // Runnable
                3000 // Delay in milliseconds
            )

        }

        // Schedule the task to repeat after 1 second
        mHandler.postDelayed(
            mRunnable, // Runnable
            1000 // Delay in milliseconds
        )
    }

    fun Toast.showCustomToast(message: String, activity: Activity)
    {
        val layout = activity.layoutInflater.inflate (
            R.layout.dialog_phone_certify,
            activity.findViewById(R.id.toast_container)
        )

        // set the text of the TextView of the message
        val textView = layout.findViewById<TextView>(R.id.toast_text)
        textView.text = message

        // use the application extension function
        this.apply {
            setGravity(Gravity.TOP, 0, 120)
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }
    }


}
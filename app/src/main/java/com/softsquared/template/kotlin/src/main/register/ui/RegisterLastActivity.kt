package com.softsquared.template.kotlin.src.main.register.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityRegisterLastBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.register.network.SignUpFragmentView
import com.softsquared.template.kotlin.src.main.register.network.SignUpService
import com.softsquared.template.kotlin.src.main.register.models.PostSignUpRequest
import com.softsquared.template.kotlin.src.main.register.dialog.DialogPhoneNum
import com.softsquared.template.kotlin.src.main.register.models.SignUpResponse
import kotlin.math.roundToInt

class RegisterLastActivity : BaseActivity<ActivityRegisterLastBinding>(ActivityRegisterLastBinding::inflate),
    SignUpFragmentView {

    val TAG = "tag"
    private lateinit var mHandler: Handler
    private lateinit var mRunnable:Runnable
    private val prefEdit = ApplicationClass.sSharedPreferences.edit()

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

        if (intent.hasExtra("phone")) {
            // 이전 Activity 에서 입력받은 phoneNum 정보 넣기
            val phoneNum = intent.getStringExtra("phone")
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


        // 회원가입 api 연결
        // My 야놀자 화면으로 이동(Fragment 띄우기)
        binding.registerConfirmBtn.setOnClickListener {

            // 이전 activity 에서 입력 정보 모두 받아오기
            if(intent.hasExtra("email") && intent.hasExtra("pw") && intent.hasExtra("phone")){
                val email = intent.getStringExtra("email")!!
                val password = intent.getStringExtra("pw")!!

                // 010-1111-1111
                val phoneWithHypen = intent.getStringExtra("phone")!!

                // 01011111111 (서버 형식에 데이터 맞추기)
                val phoneSplit = phoneWithHypen.split("-")
                val phone = phoneSplit[0] + phoneSplit[1] + phoneSplit[2]

                val nickNamSplit = email.split("@")
                val nickname = nickNamSplit[0]

                val postRequest = PostSignUpRequest(nickname=nickname,email=email,phone,password =password)
                Log.d(TAG,phone)
                Log.d(TAG,phoneWithHypen)

                showLoadingDialog(this)

                SignUpService(this).tryPostSignUp(postRequest)

            }
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

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        dismissLoadingDialog()
        Log.d(TAG, "Get JWT 성공")

        // 회원가입 성공
        if(response.code == 1000){

            // jwt 토큰 저장하기
            prefEdit.putString(ApplicationClass.X_ACCESS_TOKEN,response.result.jwt)

            // 로그(인)으로 상태변경
            ApplicationClass.loginState = 1

            // 로그인 상태 저장(for 자동 로그인)
            prefEdit.putInt("saveLogin", ApplicationClass.loginState).apply()

            // 메인화면으로 이동
            val intent = Intent(this,MainActivity::class.java)

            // MY 야놀자 페이지로 바로 이동하게 하기 위한 코드
            intent.putExtra("afterLoginTask",true)

            startActivity(intent)
        }else{ // 오류
            Log.e(TAG, response.code.toString())
        }
    }

    // 회원가입 실패
    override fun onPostSignUpFailure(message: String) {
        dismissLoadingDialog()
        Log.e(TAG, message)
        showCustomToast("아예연결못함")
    }


}
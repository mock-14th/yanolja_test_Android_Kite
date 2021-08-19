package com.softsquared.template.kotlin.src.main.register.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.main.register.ui.RegisterActivity2
import com.softsquared.template.kotlin.src.main.register.ui.RegisterActivity3

class FragmentAgreeBtm(val email:String, val pw:String) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // 화면에 띄울 뷰를 지정
        var view:View = inflater.inflate(R.layout.dialog_sign_up_agree, container, false)


        // 확인 버튼 클릭 막기
        view.findViewById<Button>(R.id.agree_nextBtn).isClickable  = true

        // 창닫기 클릭 리스너 선언
        view.findViewById<ImageButton>(R.id.agree_closeBtn).setOnClickListener { view ->
            //해당 프래그먼트 창 닫기
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().remove(this).commit()
            fragmentManager.popBackStack()
        }

        view.findViewById<CheckBox>(R.id.agree_all_check).setOnCheckedChangeListener { buttonView, isChecked ->
            // 버튼 클릭 가능하게 하기
            view.findViewById<Button>(R.id.agree_nextBtn).isClickable  = true
            view.findViewById<Button>(R.id.agree_nextBtn).setBackgroundResource(R.drawable.btn_login_active)
        }

        view.findViewById<CheckBox>(R.id.agree_all_check).setOnClickListener {
            // 하단 체크 색깔 체우기
            view.findViewById<ImageButton>(R.id.agree_check_iv).setBackgroundResource(R.drawable.ic_agree_check_confirm)
            view.findViewById<ImageButton>(R.id.agree_check_iv2).setBackgroundResource(R.drawable.ic_agree_check_confirm)
            view.findViewById<ImageButton>(R.id.agree_check_iv3).setBackgroundResource(R.drawable.ic_agree_check_confirm)
            view.findViewById<ImageButton>(R.id.agree_check_iv4).setBackgroundResource(R.drawable.ic_agree_check_confirm)
        }

        // 다음 액티비티로 이동
        view.findViewById<Button>(R.id.agree_nextBtn).setOnClickListener {

            val nextIntent = Intent(context as RegisterActivity2, RegisterActivity3::class.java)

            nextIntent.putExtra("email",email)
            nextIntent.putExtra("pw",pw)

            startActivity(nextIntent)


        }



        return view
    }

}
package com.softsquared.template.kotlin.src.main.login.register

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.softsquared.template.kotlin.R

class FragmentAgreeBtm : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // 화면에 띄울 뷰를 지정
        var view:View = inflater.inflate(R.layout.dialog_sign_up_agree, container, false)

        // 창닫기 클릭 리스너 선언
//        view.findViewById<ImageButton>(R.id.agree_closeBtn).setOnClickListener { view ->
//            RegisterActivity2?.onButtonClicked()
//            //해당 프래그먼트 창 닫기
//            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
//            fragmentManager.beginTransaction().remove(this).commit()
//            fragmentManager.popBackStack()
//        }

        return view
    }
}
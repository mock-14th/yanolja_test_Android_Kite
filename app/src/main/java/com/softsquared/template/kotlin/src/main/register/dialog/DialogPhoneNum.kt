package com.softsquared.template.kotlin.src.main.register.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.softsquared.template.kotlin.databinding.DialogPhoneCertifyBinding

class DialogPhoneNum : DialogFragment() {
    private var _binding: DialogPhoneCertifyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogPhoneCertifyBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
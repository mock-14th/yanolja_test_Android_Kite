package com.softsquared.template.kotlin.src.main.region.ui

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityPersonNumCntBinding

class PersonNumCntActivity :
    BaseActivity<ActivityPersonNumCntBinding>(ActivityPersonNumCntBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var numCnt = 2

        // 인원 수 증가
        binding.plusCtnBtn.setOnClickListener {

            numCnt++

            binding.cntNumTv.text = numCnt.toString()
            binding.selectPerCntBtn.text = "성인 " + numCnt.toString() + ", 아동 0 · 적용하기"

        }

        // 인원 수 감소
        binding.minusCntBtn.setOnClickListener {
            numCnt--

            binding.cntNumTv.text = numCnt.toString()
            binding.selectPerCntBtn.text = "성인 " + numCnt.toString() + ", 아동 0 · 적용하기"

        }

        // 명수 데이터 넘겨주기
        binding.selectPerCntBtn.setOnClickListener {

            intent.putExtra("numCnt", numCnt)

            setResult(RESULT_OK,intent)

            finish()

        }


    }
}
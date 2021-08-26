package com.softsquared.template.kotlin.src.main.book.ui

import android.content.Intent
import android.os.Bundle
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityBookBinding

class BookActivity:BaseActivity<ActivityBookBinding>(ActivityBookBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startDate = intent.getStringExtra("startDate")
        val endDate = intent.getStringExtra("endDate")
        val brandName = intent.getStringExtra("brandName")
        val roomType = intent.getStringExtra("roomType")

        val perCnt = intent.getStringExtra("perCnt")
        val forUIcheckInDate = intent.getStringExtra("forUIcheckInDate")
        val forUIcheckOutDate = intent.getStringExtra("forUIcheckOutDate")


        // UI 변경
        binding.bookRoomTypeTv.text = roomType
        binding.roomPerCntTv.text = perCnt
        binding.checkInForUiTv.text = forUIcheckInDate
        binding.checkOutForUiTv.text = forUIcheckOutDate


        // 예약 조회 api를 완성하기 위한 정보 넘겨주기 (startDate, endDate, brandName, RoomType)
        binding.bookNowBtn.setOnClickListener {
            val intent = Intent(this, BookPutInfoActivity::class.java)

            intent.putExtra("startDate",startDate)
            intent.putExtra("endDate",endDate)
            intent.putExtra("brandName",brandName)
            intent.putExtra("roomType",roomType)

            startActivity(intent)
        }

    }
}
package com.softsquared.template.kotlin.src.main.region.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.checkInAndOutDate
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.checkInDate
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.checkInDayOfWeek
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.checkInMonth
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.checkOutDate
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.checkOutDayOfWeek
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.checkOutMonth
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityCalendarBinding
import com.softsquared.template.kotlin.databinding.ActivityRegionRoomListBinding
import com.softsquared.template.kotlin.databinding.DialogSetDayAndCntBinding
import com.softsquared.template.kotlin.src.main.region.RegionRoomListActivity
import java.time.format.DateTimeFormatter

class CalendarActivity : BaseActivity<ActivityCalendarBinding>(ActivityCalendarBinding::inflate) {

    private lateinit var binding2: DialogSetDayAndCntBinding
    private lateinit var binding3: ActivityRegionRoomListBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding2 = DialogSetDayAndCntBinding.inflate(layoutInflater)
        binding3 = ActivityRegionRoomListBinding.inflate(layoutInflater)


        var formater = DateTimeFormatter.ofPattern("dd MMM yyyy")
        var btnTxt = binding.selectDateBtn.text




        binding.calendarView.selectionManager = RangeSelectionManager(OnDaySelectedListener {
//            Log.e(" CALENDAR ", "========== setSelectionManager ==========")
//            Log.e(" CALENDAR ", "Selected Dates : " + binding.calendarView.selectedDates.size)
            if (binding.calendarView.selectedDates.size <= 0) return@OnDaySelectedListener
//            Log.e(" CALENDAR ", "Selected Days : " + binding.calendarView.selectedDays)
//
//            Log.e(" CALENDAR ", "Selected Days Size : " + binding.calendarView.selectedDays.size)
//
//            Log.e(" CALENDAR ", "get Selected Days : " + binding.calendarView.selectedDates)
//
            Log.e(
                " CALENDAR ",
                "Departure : DD MMM YYYY " + binding.calendarView.selectedDays[0].calendar.time
            )
            Log.e(
                " CALENDAR ",
                "Return : DD MMM YYYY " + binding.calendarView.selectedDays[binding.calendarView.selectedDates.size - 1].calendar.time
            )

            var startDate = binding.calendarView.selectedDays[0].calendar.time
            var endDate = binding.calendarView.selectedDays[binding.calendarView.selectedDates.size - 1].calendar.time


            // 월
            if (startDate.toString().contains("Aug")) {
                checkInMonth = "8"
            } else if (startDate.toString().contains("Sep")) {
                checkInMonth = "9"
            } else if (startDate.toString().contains("Oct")) {
                checkInMonth = "10"
            }

            if (endDate.toString().contains("Aug")) {
                checkOutMonth = "8"
            } else if (endDate.toString().contains("Sep")) {
                checkOutMonth = "9"
            } else if (endDate.toString().contains("Oct")) {
                checkOutMonth = "10"
            }


            // 요일
            if (startDate.toString().contains("Mon")) {
                checkInDayOfWeek = "월"
            } else if (startDate.toString().contains("Tue")) {
                checkInDayOfWeek = "화"
            } else if (startDate.toString().contains("Wed")) {
                checkInDayOfWeek = "수"
            } else if (startDate.toString().contains("Thu")) {
                checkInDayOfWeek = "목"
            } else if (startDate.toString().contains("Fri")) {
                checkInDayOfWeek = "금"
            } else if (startDate.toString().contains("Sat")) {
                checkInDayOfWeek = "토"
            } else if (startDate.toString().contains("Sun")) {
                checkInDayOfWeek = "일"
            }

            if (endDate.toString().contains("Mon")) {
                checkOutDayOfWeek = "월"
            } else if (endDate.toString().contains("Tue")) {
                checkOutDayOfWeek = "화"
            } else if (endDate.toString().contains("Wed")) {
                checkOutDayOfWeek = "수"
            } else if (endDate.toString().contains("Thu")) {
                checkOutDayOfWeek = "목"
            } else if (endDate.toString().contains("Fri")) {
                checkOutDayOfWeek = "금"
            } else if (endDate.toString().contains("Sat")) {
                checkOutDayOfWeek = "토"
            } else if (endDate.toString().contains("Sun")) {
                checkOutDayOfWeek = "일"
            }


            val tokenStartDate = startDate.toString().chunked(2)
            val tokenEndDate = endDate.toString().chunked(2)

            // 체크 인 (일)
            if (tokenStartDate[4].startsWith("0")) {
                checkInDate = tokenStartDate[4][1].toString()
            } else {
                checkInDate = tokenStartDate[4]
            }

            // 체크 아웃 (일)
            if (tokenEndDate[4].startsWith("0")) {
                checkOutDate = tokenEndDate[4][1].toString()
            } else {
                checkOutDate = tokenEndDate[4]
            }


            Log.e(" CALENDAR ", "start:$checkInDate")

            Log.e(" CALENDAR ", "end:$checkOutDate")

            // 버튼 텍스트 설정
            if (startDate == endDate) {
                binding.selectDateBtn.text =
                    checkInMonth + "월 " + checkInDate + "일(" + checkInDayOfWeek + ") 체크인 검색"
            } else {
                binding.selectDateBtn.text =
                    checkInMonth + "월 " + checkInDate + "일(" + checkInDayOfWeek + ") ~ " + checkOutMonth + "월 " + checkOutDate + "일(" + checkOutDayOfWeek + ") · " + (binding.calendarView.selectedDates.size - 1) + "박"
            }

        })


        // 날짜 정보 넘겨주기
        binding.selectDateBtn.setOnClickListener {

            checkInAndOutDate = binding.selectDateBtn.text.toString()


            // 모텔 리스트 조회 activity
            intent.putExtra("startDate", "2021-0$checkInMonth-$checkInDate")

            intent.putExtra("endDate", "2021-0$checkOutMonth-$checkOutDate")

            intent.putExtra("showingDate", "0"+ checkInMonth + "." + checkInDate + "~" + "0" + checkOutMonth + "." + checkOutDate)


            // 모텔 정보 상세 조회 activity
            intent.putExtra("checkInDate","${checkInMonth}월 ${checkInDate}일($checkInDayOfWeek)")

            intent.putExtra("checkOutDate","${checkOutMonth}월 ${checkOutDate}일($checkOutDayOfWeek)")

            intent.putExtra("sendStartDate","20210$checkInMonth$checkInDate")
            intent.putExtra("sendEndDate","20210$checkOutMonth$checkOutDate")

            setResult(RESULT_OK,intent)

            finish()
        }


    }
}
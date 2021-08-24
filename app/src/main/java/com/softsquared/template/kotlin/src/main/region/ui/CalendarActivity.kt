package com.softsquared.template.kotlin.src.main.region.ui

import android.os.Bundle
import android.util.Log
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityCalendarBinding

class CalendarActivity:BaseActivity<ActivityCalendarBinding>(ActivityCalendarBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.calendarView.selectionManager  = RangeSelectionManager(OnDaySelectedListener {
            Log.e(" CALENDAR ", "========== setSelectionManager ==========")
            Log.e(" CALENDAR ", "Selected Dates : " + binding.calendarView.selectedDates.size)
            if (binding.calendarView.selectedDates.size <= 0) return@OnDaySelectedListener
            Log.e(" CALENDAR ", "Selected Days : " + binding.calendarView.selectedDays)
        })
    }
}
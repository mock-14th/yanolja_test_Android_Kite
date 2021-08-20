package com.softsquared.template.kotlin.src.main.home.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.softsquared.template.kotlin.R

class RecoViewPagerAdapter(context: Context): PagerAdapter() {

    private var mContext: Context?=null
    private var context:Context = context

    fun ViewPagerAdapter(context: Context){
        mContext=context;
    }

    //position에 해당하는 페이지 생성
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view= LayoutInflater.from(container.context).inflate(R.layout.item_home_view_pager,container,false)

        when(position){
            0 ->  {
                    view.findViewById<ImageView>(R.id.view_home_ad_iv).setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.view_ad_1))
                    view.findViewById<TextView>(R.id.view_home_position_tv).text = "01"
                    }
            1 ->   {
                    view.findViewById<ImageView>(R.id.view_home_ad_iv).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.view_ad_2))
                    view.findViewById<TextView>(R.id.view_home_position_tv).text = "02"
                  }
            2 ->  {
                    view.findViewById<ImageView>(R.id.view_home_ad_iv).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.view_ad_3))
                    view.findViewById<TextView>(R.id.view_home_position_tv).text = "03"
                }
            3 ->   {
                    view.findViewById<ImageView>(R.id.view_home_ad_iv).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.view_ad_4))
                    view.findViewById<TextView>(R.id.view_home_position_tv).text = "04"
                }
            4 ->   {
                    view.findViewById<ImageView>(R.id.view_home_ad_iv).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.view_ad_5))
                    view.findViewById<TextView>(R.id.view_home_position_tv).text = "05"
                }
        }


        container.addView(view)
        return view
    }

    //position에 위치한 페이지 제거
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


    //사용가능한 뷰 개수 리턴
    override fun getCount(): Int {
        return 5
    }

    //페이지뷰가 특정 키 객체(key object)와 연관 되는지 여부
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }
}
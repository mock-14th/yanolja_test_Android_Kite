package com.softsquared.template.kotlin.src.main.region.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import org.w3c.dom.Text

class HotelInfoViewPagerAdapter(context: Context,itemList: List<String>): PagerAdapter() {

    private var mContext: Context?=null
    private var context: Context = context
    private var itemList = itemList

    fun ViewPagerAdapter(context: Context){
        mContext=context;
    }

    //position에 해당하는 페이지 생성
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view= LayoutInflater.from(container.context).inflate(R.layout.item_viewpager_room,container,false)

        Glide.with(context).load(itemList[position]).into(view.findViewById<ImageView>(R.id.item_viewpager_hotel_iv))
        view.findViewById<TextView>(R.id.pager_photo_position_tv).text = (position+1).toString() + "/" + itemList.size

        container.addView(view)
        return view
    }

    //position에 위치한 페이지 제거
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


    //사용가능한 뷰 개수 리턴
    override fun getCount(): Int {
        return itemList.size
    }

    //페이지뷰가 특정 키 객체(key object)와 연관 되는지 여부
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }
}
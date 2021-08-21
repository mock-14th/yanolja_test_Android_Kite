package com.softsquared.template.kotlin.src.main.home.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.createBitmap
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemLocalRecentProductBinding
import com.softsquared.template.kotlin.databinding.ItemRecoNhBinding
import com.softsquared.template.kotlin.src.main.home.data.LocalRecentData
import com.softsquared.template.kotlin.src.main.home.data.RecoNewHotData

class LocalRecentAdapter(var itemList:ArrayList<LocalRecentData>, val fragment_s: Fragment, var context: Context): RecyclerView.Adapter<RecentItemHolder>() {

    lateinit var fragment: Fragment
    lateinit var binding: ItemLocalRecentProductBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentItemHolder {
        val binding = ItemLocalRecentProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        this.fragment = fragment_s
        return RecentItemHolder(binding, context)
    }

    override fun onBindViewHolder(holder: RecentItemHolder, position: Int) {
        val nhList = itemList[position]
        holder.setItemList(nhList)
    }

    override fun getItemCount(): Int = itemList.size
}

class RecentItemHolder(val binding: ItemLocalRecentProductBinding, var context: Context): RecyclerView.ViewHolder(binding.root) {
    fun setItemList(itemList:LocalRecentData){

        // 상품 이름
        binding.recentRoomTv.text = itemList.itmTxt

        // 상품 이미지
        binding.recentIv.setImageDrawable(itemList.itemImg)

        // 상품 별점(ratingar)
        binding.ratingBar.rating = itemList.ratingBar.toFloat()

        // 별점
        binding.ratingTv.text = itemList.ratingTxt

        // 댓글 수
        binding.recentComment.text = itemList.commentTxt

        // 할인률
        binding.tvPrice.text = itemList.saleTxt

        // 가격
        binding.tvMoney.text = itemList.priceTxt
    }
}
package com.softsquared.template.kotlin.src.main.region.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.databinding.ItemRegionMotelListBinding
import com.softsquared.template.kotlin.databinding.ItemTabRegionMainBinding
import com.softsquared.template.kotlin.src.main.region.data.MotelListData
import com.softsquared.template.kotlin.src.main.region.data.RegionMainData

class MotelListAdapter(var itemList:ArrayList<MotelListData>, val fragment_s: Fragment, var context: Context): RecyclerView.Adapter<MotelListAdapter.MotelItemHolder>() {

    lateinit var fragment: Fragment
    lateinit var binding: ItemRegionMotelListBinding

    var rowIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotelListAdapter.MotelItemHolder {
        val binding = ItemRegionMotelListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        this.fragment = fragment_s
        return MotelItemHolder(binding, context)
    }


    override fun onBindViewHolder(holder: MotelItemHolder, position: Int) {
        val myItemList = itemList[position]
        holder.setItemList(myItemList)
    }

    // 아이템 클릭 리스너
    override fun getItemCount(): Int = itemList.size

    interface OnItemClickListener{
        fun onItemClick(v: View, data: MotelListData, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener, function: () -> Unit) {
        this.listener = listener
    }

    inner class MotelItemHolder(val binding: ItemRegionMotelListBinding, var context: Context): RecyclerView.ViewHolder(binding.root) {
        fun setItemList(itemList: MotelListData){


            // 숙박업소명
            binding.motelItemMotelNameTv.text = itemList.motelNameTxt

            // 대표사진
            Glide.with(itemView).load(itemList.photoIV).into(binding.itemHotelIv)

            // 평균별점
            binding.motelItemRatingbar.rating = itemList.rating!!.toFloat()
            binding.motelItemRateTv.text = itemList.rating

            // 후기개수
            binding.motelItemCommentNumTv.text = itemList.commentCnt.toString()

            // 대실가격
            binding.itemRentPriceTv.text = itemList.rentPrice

            // 숙박가격
            binding.itemSleepPriceTv.text = itemList.sleepPrcie

            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView,itemList,pos)
                    rowIndex = pos
                    notifyDataSetChanged()
                }
            }
        }
    }

    // px을 dp 단위로 바꿔주는 코드 (layoutParamas가 px로만 값을 받기 때문에 바꿔줘야 한다.)
    fun Int.toPx():Int = (this * Resources.getSystem().displayMetrics.density).toInt()


}

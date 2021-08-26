package com.softsquared.template.kotlin.src.main.region.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.databinding.ItemRegionHotelListBinding
import com.softsquared.template.kotlin.src.main.region.data.HotelListData
import com.softsquared.template.kotlin.src.main.region.data.MotelListData

class HotelListAdapter(
    var itemList: ArrayList<HotelListData>,
    val fragment_s: Fragment,
    var context: Context
) : RecyclerView.Adapter<HotelListAdapter.HotelItemHolder>() {

    lateinit var fragment: Fragment
    lateinit var binding: ItemRegionHotelListBinding

    var rowIndex = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotelListAdapter.HotelItemHolder {
        val binding =
            ItemRegionHotelListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        this.fragment = fragment_s
        return HotelItemHolder(binding, context)
    }


    override fun onBindViewHolder(holder: HotelListAdapter.HotelItemHolder, position: Int) {
        val myItemList = itemList[position]
        holder.setItemList(myItemList)
    }

    // 아이템 클릭 리스너
    override fun getItemCount(): Int = itemList.size

    interface OnItemClickListener {
        fun onItemClick(v: View, data: HotelListData, pos: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener, function: () -> Unit) {
        this.listener = listener
    }

    inner class HotelItemHolder(val binding: ItemRegionHotelListBinding, var context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItemList(itemList: HotelListData) {

            //"숙박업소명": "호텔 크레센도 서울",
            //"대표사진": "https://sug
            //"평균별점": "0",
            //"후기갯수": 0,
            //"숙박가격": "230000"

            // 숙박 업소명
            binding.hotelItemMotelNameTv.text = itemList.hotelName

            // 대표사진
            Glide.with(context).load(itemList.hotelImg).into(binding.itemHotelIv)

            // 평균별점
            binding.hotelItemRateTv.text = itemList.avgRate
            binding.hotelItemRatingbar.rating = itemList.avgRate.toFloat()

            // 후기갯수
            binding.motelItemCommentNumTv.text = "(" + itemList.commentCnt + ")"

            // 숙박가격
            val wordLength = itemList.hotelPrice.length
            binding.itemSleepPriceTv.text = itemList.hotelPrice.substring(0,2) + "," + itemList.hotelPrice.substring(2,wordLength)

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, itemList, pos)
                    rowIndex = pos
                    notifyDataSetChanged()
                }
            }
        }
    }


}

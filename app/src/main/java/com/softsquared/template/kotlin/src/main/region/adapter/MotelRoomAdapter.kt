package com.softsquared.template.kotlin.src.main.region.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.softsquared.template.kotlin.databinding.ItemMotelRoomListBinding
import com.softsquared.template.kotlin.src.main.region.ui.MotelInfoActivity
import com.softsquared.template.kotlin.src.main.region.data.MotelRoomData
import com.softsquared.template.kotlin.src.main.region.ui.RoomDetailActivity
import java.text.DecimalFormat

class MotelRoomAdapter(context: MotelInfoActivity, var itemList: ArrayList<MotelRoomData>) :
    RecyclerView.Adapter<MRoomHolder>() {
    private lateinit var binding: ItemMotelRoomListBinding
    var context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MRoomHolder {
        val binding =
            ItemMotelRoomListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MRoomHolder(binding, context)
    }

    override fun onBindViewHolder(holder: MRoomHolder, position: Int) {
        val roomList = itemList.get(position)
        holder.setRoomList(roomList)

        // 룸 상세 정보 조회 화면으로 이동
        holder.itemView.setOnClickListener {
            Intent(context, RoomDetailActivity::class.java).apply {
                // 방 타입 전달
                putExtra("roomType", itemList[position].roomTypeTxt.toString())

                // 특정 숙소의 ID
                putExtra("brandId", itemList[position].brandId)

                // startDate
                putExtra("startDate", itemList[position].startDate.toString())

                // endDate
                putExtra("endDate", itemList[position].endDate.toString())

                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { context.startActivity(this) }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}

class MRoomHolder(val binding: ItemMotelRoomListBinding, var context: Context) :
    RecyclerView.ViewHolder(binding.root) {

    var formatter = DecimalFormat("###,###")

    fun setRoomList(roomList: MotelRoomData) {

        // 대표이미지
        Glide.with(itemView).load(roomList.brandImg).transform(RoundedCorners(5)).into(binding.brandImgIv)

        // 방 타입
        binding.roomTypeTv.text = roomList.roomTypeTxt

        // 방 옵션
        binding.roomOptionTv.text = roomList.roomOptionTxt

        // 기준 명 수
        binding.gizoonCntTv.text = roomList.gizoonCnt.toString()

        // 최대 명 수
        binding.maxCntTv.text = roomList.maxCnt.toString()

        // 대실 가격
        if (roomList.rentPriceTxt != "예약마감") {
            binding.rentPriceTv.text = formatter.format(roomList.rentPriceTxt.toInt()) + "원"
        } else {
            binding.rentPriceTv.text = roomList.rentPriceTxt
        }

        // 숙박 가격
        if (roomList.sleepPrcieTxt != "예약마감") {
            binding.sleepPriceTv.text = formatter.format(roomList.sleepPrcieTxt.toInt()) + "원"
        } else {
            binding.sleepPriceTv.text = roomList.sleepPrcieTxt
        }

        // 대여 시간
        binding.checkInTimeTv.text = roomList.rentTimeTxt

    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)

    }

    //를릭 리스너
    private lateinit var itemClickListner: ItemClickListener
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }
}
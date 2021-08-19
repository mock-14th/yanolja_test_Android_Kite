package com.softsquared.template.kotlin.src.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemRecoNhBinding
import com.softsquared.template.kotlin.src.main.home.data.RecoNewHotData

class RecoNewHotAdapter(var itemList:ArrayList<RecoNewHotData>, val fragment_s: Fragment, var context: Context): RecyclerView.Adapter<NHolder>() {

    lateinit var fragment: Fragment
    lateinit var binding: ItemRecoNhBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NHolder {
        val binding = ItemRecoNhBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        this.fragment = fragment_s
        return NHolder(binding, context)
    }

    override fun onBindViewHolder(holder: NHolder, position: Int) {
        val nhList = itemList[position]
        holder.setItemList(nhList)
    }

    override fun getItemCount(): Int = itemList.size
}

class NHolder(val binding: ItemRecoNhBinding, var context: Context): RecyclerView.ViewHolder(binding.root) {
    fun setItemList(itemList:RecoNewHotData){

        // 이미지 설정
        binding.recoIvImg.setImageDrawable(itemList.itemImg)

        // 텍스트 설정
        binding.recoTvItem.text = itemList.itemTxt

        binding.recoIvBg.background = binding.recoIvBg.background

    }
}
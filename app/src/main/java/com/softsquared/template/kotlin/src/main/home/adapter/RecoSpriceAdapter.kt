package com.softsquared.template.kotlin.src.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.createBitmap
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemRecoNhBinding
import com.softsquared.template.kotlin.databinding.ItemRecoSpeicalPriceBinding
import com.softsquared.template.kotlin.src.main.home.data.RecoNewHotData
import com.softsquared.template.kotlin.src.main.home.data.RecoSpriceData

class RecoSpriceAdapter(var itemList:ArrayList<RecoSpriceData>, val fragment_s: Fragment, var context: Context): RecyclerView.Adapter<SPHolder>() {

    lateinit var fragment: Fragment
    lateinit var binding: ItemRecoSpeicalPriceBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SPHolder {
        val binding = ItemRecoSpeicalPriceBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        this.fragment = fragment_s
        return SPHolder(binding, context)
    }

    override fun onBindViewHolder(holder: SPHolder, position: Int) {
        val spList = itemList[position]
        holder.setItemList(spList)
    }

    override fun getItemCount(): Int = itemList.size
}

class SPHolder(val binding: ItemRecoSpeicalPriceBinding, var context: Context): RecyclerView.ViewHolder(binding.root) {
    fun setItemList(itemList: RecoSpriceData){

        // 할인가격 텍스트
        binding.textPercent.text = itemList.saleTxt

        // 안내 텍스트(1)
        binding.specialTvOne.text = itemList.txtOne

        // 안내 텍스트(2)
        binding.specialTvTwo.text = itemList.txtTwo

        // 안내 텍스트(3)
        binding.specialTvThree.text = itemList.txtThree

        // 배경 색
        binding.specialBgCl.background = itemList.bgColor


    }
}
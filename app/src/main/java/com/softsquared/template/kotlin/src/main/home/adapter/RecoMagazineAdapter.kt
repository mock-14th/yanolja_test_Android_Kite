package com.softsquared.template.kotlin.src.main.home.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemRecoMagazineBinding
import com.softsquared.template.kotlin.src.main.home.data.RecoMagazineData

class RecoMagazineAdapter(var itemList:ArrayList<RecoMagazineData>, val fragment_s: Fragment, var context: Context): RecyclerView.Adapter<MagazineHolder>() {

    lateinit var fragment: Fragment
    lateinit var binding: ItemRecoMagazineBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagazineHolder {
        val binding = ItemRecoMagazineBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        this.fragment = fragment_s
        return MagazineHolder(binding, context)
    }

    override fun onBindViewHolder(holder: MagazineHolder, position: Int) {
        val magazineList = itemList[position]
        holder.setItemList(magazineList)
    }

    override fun getItemCount(): Int = itemList.size
}

class MagazineHolder(val binding: ItemRecoMagazineBinding, var context: Context): RecyclerView.ViewHolder(binding.root) {
    fun setItemList(itemList:RecoMagazineData){

        // 이미지 설정
        binding.recoMagazineIv.setImageDrawable(itemList.itemImg)

        // 이미지 밝기 조절
        binding.recoMagazineIv.setColorFilter(Color.parseColor("#BDBDBD"),PorterDuff.Mode.MULTIPLY)

        // 텍스트 설정
        binding.magazineInfoTv.text = itemList.itemTxt


    }
}
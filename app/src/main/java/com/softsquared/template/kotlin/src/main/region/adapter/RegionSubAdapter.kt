import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemTabRegionMainBinding
import com.softsquared.template.kotlin.databinding.ItemTabRegionSubBinding
import com.softsquared.template.kotlin.src.main.region.data.RegionMainData
import com.softsquared.template.kotlin.src.main.region.data.RegionSubData


class RegionSubAdapter(var itemList:ArrayList<RegionSubData>, val fragment_s: Fragment, var context: Context): RecyclerView.Adapter<RSItemHolder>() {

    lateinit var fragment: Fragment
    lateinit var binding: ItemTabRegionSubBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSItemHolder {
        val binding = ItemTabRegionSubBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        this.fragment = fragment_s
        return RSItemHolder(binding, context)
    }

    override fun onBindViewHolder(holder: RSItemHolder, position: Int) {
        val myItemList = itemList[position]
        holder.setItemList(myItemList)
    }

    override fun getItemCount(): Int = itemList.size
}

class RSItemHolder(val binding: ItemTabRegionSubBinding, var context: Context): RecyclerView.ViewHolder(binding.root) {
    fun setItemList(itemList: RegionSubData){

        binding.tabRegionSubTv.text = itemList.itemTxt
    }
}
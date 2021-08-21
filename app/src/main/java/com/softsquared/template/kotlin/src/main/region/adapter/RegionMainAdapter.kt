import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemTabRegionMainBinding
import com.softsquared.template.kotlin.src.main.region.data.RegionMainData

class RegionMainAdapter(var itemList:ArrayList<RegionMainData>, val fragment_s: Fragment, var context: Context): RecyclerView.Adapter<RMItemHolder>() {

    lateinit var fragment: Fragment
    lateinit var binding: ItemTabRegionMainBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RMItemHolder {
        val binding = ItemTabRegionMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        this.fragment = fragment_s
        return RMItemHolder(binding, context)
    }

    override fun onBindViewHolder(holder: RMItemHolder, position: Int) {
        val myItemList = itemList[position]
        holder.setItemList(myItemList)
    }

    override fun getItemCount(): Int = itemList.size
}

class RMItemHolder(val binding: ItemTabRegionMainBinding, var context: Context): RecyclerView.ViewHolder(binding.root) {
    fun setItemList(itemList:RegionMainData){

        binding.tabRegionMainTv.text = itemList.itemTxt
    }
}
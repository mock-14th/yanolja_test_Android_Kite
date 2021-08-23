import android.content.Context
import android.content.res.Resources
import android.graphics.Color
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


class RegionSubActivityAdapter(var itemList:ArrayList<RegionSubData>, var context: Context): RecyclerView.Adapter<RegionSubActivityAdapter.RSAItemHolder>() {

    lateinit var fragment: Fragment
    lateinit var binding: ItemTabRegionSubBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSAItemHolder {
        val binding = ItemTabRegionSubBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RSAItemHolder(binding, context)
    }

    override fun onBindViewHolder(holder: RSAItemHolder, position: Int) {
        // 아이템 간 간격 설정
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 55.toPx()
        holder.itemView.requestLayout()

        val myItemList = itemList[position]
        holder.setItemList(myItemList)
    }

    override fun getItemCount(): Int = itemList.size

    interface OnItemClickListener{
        fun onItemClick(v: View, data: RegionSubData, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener, function: () -> Unit) {
        this.listener = listener
    }

    fun Int.toPx():Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    inner class RSAItemHolder(val binding: ItemTabRegionSubBinding, var context: Context): RecyclerView.ViewHolder(binding.root) {
        fun setItemList(itemList: RegionSubData){

            binding.tabRegionSubTv.text = itemList.itemTxt

            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView,itemList,pos)
                }
            }
        }
    }
}

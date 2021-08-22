import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemTabRegionMainBinding
import com.softsquared.template.kotlin.src.main.region.data.RegionMainData

class RegionMainAdapter(var itemList:ArrayList<RegionMainData>, val fragment_s: Fragment, var context: Context): RecyclerView.Adapter<RegionMainAdapter.RMItemHolder>() {

    lateinit var fragment: Fragment
    lateinit var binding: ItemTabRegionMainBinding



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RMItemHolder {
        val binding = ItemTabRegionMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        this.fragment = fragment_s
        return RMItemHolder(binding, context)
    }

    override fun onBindViewHolder(holder: RMItemHolder, position: Int) {

        // 아이템 간 간격 설정
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 55.toPx()
        holder.itemView.requestLayout()

        val myItemList = itemList[position]
        holder.setItemList(myItemList)
    }

    // 아이템 클릭 리스너
    override fun getItemCount(): Int = itemList.size

    interface OnItemClickListener{
        fun onItemClick(v: View, data: RegionMainData, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener, function: () -> Unit) {
        this.listener = listener
    }

    inner class RMItemHolder(val binding: ItemTabRegionMainBinding, var context: Context): RecyclerView.ViewHolder(binding.root) {
        fun setItemList(itemList:RegionMainData){

            binding.tabRegionMainTv.text = itemList.itemTxt

            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView,itemList,pos)
                }
            }
        }
    }

    // px을 dp 단위로 바꿔주는 코드 (layoutParamas가 px로만 값을 받기 때문에 바꿔줘야 한다.)
    fun Int.toPx():Int = (this * Resources.getSystem().displayMetrics.density).toInt()


}


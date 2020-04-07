package com.example.custom_flexbox

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import com.example.library.recycler_view.BaseRecyclerAdapter
import com.example.library.recycler_view.BaseViewHolder
import com.example.custom_flexbox.databinding.ItemRecyclerviewBinding
import com.z5x.jalesh.recycler_view.OnItemClickListener

class ListAdapter(context: Context?) :
    BaseRecyclerAdapter<BaseModel, OnItemClickListener, ListAdapter.ListViewHolder>(context) {

    private var selectedPos = DiffResult.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding: ItemRecyclerviewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_recyclerview,
            parent,
            false
        )
        return ListViewHolder(binding, this)
    }

    private fun setExpanded(pos: Int) {
        getItem(pos).isSelected = !getItem(pos).isSelected
        notifyItemChanged(pos)
//        if (selectedPos != DiffResult.NO_POSITION) {
//            if (selectedPos == pos) {
//                selectItem(selectedPos, !getItem(selectedPos).isSelected)
//            } else {
//                selectItem(selectedPos, false)
//                selectedPos = pos
//                selectItem(selectedPos, true)
//            }
//        } else {
//            selectItem(pos, true)
//        }
    }

    private fun selectItem(pos: Int, expanded: Boolean) {
        getItem(pos).isSelected = expanded
        notifyItemChanged(pos)
        selectedPos = if (expanded) pos else selectedPos
    }

    class ListViewHolder(
        itemView: ItemRecyclerviewBinding,
        listAdapter: ListAdapter
    ) :
        BaseViewHolder<BaseModel, OnItemClickListener?>(itemView.root) {

        private var context: Context
        private val binding: ItemRecyclerviewBinding = itemView

        init {
            context = binding.root.context
            binding.textView.setOnClickListener {
                /*listAdapter.getItem(adapterPosition).isSelected =
                    !listAdapter.getItem(adapterPosition).isSelected
                listAdapter.notifyItemChanged(adapterPosition)*/
                listAdapter.setExpanded(adapterPosition)
            }
        }

        override fun onBind(item: BaseModel, listener: OnItemClickListener?) {
            binding.data = item
            if (item.isSelected) {
                binding.textView.background =
                    context.resources.getDrawable(R.drawable.bg_shape_selected)
                binding.textView.setTextColor(context.resources.getColor(R.color.white))
            } else {
                binding.textView.background = context.resources.getDrawable(R.drawable.bg_shape)
                binding.textView.setTextColor(context.resources.getColor(R.color.black))
            }
        }
    }
}
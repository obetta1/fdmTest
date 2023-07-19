package com.ben.papytest.data

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ben.papytest.R
import com.ben.papytest.SectionHeader
import com.ben.papytest.SectionItem
import com.ben.papytest.databinding.ItemHeaderBinding
import com.ben.papytest.databinding.PlaceItemsBinding


class PlaceAdapter(private val dataList: List<Any>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }
    inner class HeaderViewHolder(private val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(header: SectionHeader) {
            binding.dateHeader.text = header.title
        }
    }
    inner class ItemViewHolder(private val binding: PlaceItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SectionItem) {
            binding.placeNameTv.text = item.name
            binding.scheduleTimeTv.text = item.time
            binding.addressTv.text = item.address
            when (item.status) {
                "completed" -> {
                    binding.statusCV.setCardBackgroundColor(context.getColor(R.color.light_green))
                    binding.statusTv.setText(R.string.completed)
                }
                "in progress" -> {

                    binding.statusCV.setCardBackgroundColor(context.getColor(R.color.light_blue))
                    binding.statusTv.setText(R.string.in_progress)
                }
                else -> {
                    binding.statusTv.text = item.status
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            VIEW_TYPE_ITEM -> {
                val binding = PlaceItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = dataList[position]) {
            is SectionHeader -> (holder as HeaderViewHolder).bind(item)
            is SectionItem -> (holder as ItemViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is SectionHeader -> VIEW_TYPE_HEADER
            is SectionItem -> VIEW_TYPE_ITEM
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }
    override fun getItemCount(): Int = dataList.size
}
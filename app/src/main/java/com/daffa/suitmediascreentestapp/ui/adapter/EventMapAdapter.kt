package com.daffa.suitmediascreentestapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daffa.suitmediascreentestapp.databinding.ItemEventMapBinding
import com.daffa.suitmediascreentestapp.model.entity.EventEntity

class EventMapAdapter : RecyclerView.Adapter<EventMapAdapter.EventMapAdapterViewHolder>() {

    private lateinit var onItemClicked: OnItemClicked
    private val listItem = ArrayList<EventEntity>()

    fun setOnItemClick(onItemClick: OnItemClicked) {
        this.onItemClicked = onItemClick
    }

    fun setEvent(events: List<EventEntity>?) {
        if (events.isNullOrEmpty()) return
        this.listItem.apply {
            clear()
            addAll(events)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventMapAdapter.EventMapAdapterViewHolder {
        val view = ItemEventMapBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventMapAdapterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: EventMapAdapter.EventMapAdapterViewHolder,
        position: Int
    ) = holder.bind(eventMap = listItem[position])

    override fun getItemCount(): Int = listItem.size

    inner class EventMapAdapterViewHolder(private val binding: ItemEventMapBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(eventMap: EventEntity) {
            Glide.with(itemView.context)
                .load(eventMap.image)
                .into(binding.imgEvent)

            binding.tvNameEvent.text = eventMap.name
            binding.tvDate.text = eventMap.date

            itemView.setOnClickListener{
                onItemClicked.itemClicked(adapterPosition)
            }
        }
    }

    interface OnItemClicked {
        fun itemClicked(position: Int)
    }
}
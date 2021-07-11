package com.daffa.suitmediascreentestapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daffa.suitmediascreentestapp.databinding.ItemEventBinding
import com.daffa.suitmediascreentestapp.model.entity.EventEntity

class EventAdapter : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private lateinit var onItemClick: OnItemClick
    private val listItem = ArrayList<EventEntity>()

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    fun setEvent(events: List<EventEntity>?) {
        if (events.isNullOrEmpty()) return
        this.listItem.apply {
            clear()
            addAll(events)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
        holder.bind(event = listItem[position])

    override fun getItemCount(): Int = listItem.size

    inner class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: EventEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(event.image)
                    .into(imgEvent)

                tvTitleEvent.text = event.name
                tvDate.text = event.date

                itemView.setOnClickListener {
                    onItemClick.itemClick(event)
                }
            }
        }
    }

    interface OnItemClick {
        fun itemClick(event: EventEntity)
    }
}
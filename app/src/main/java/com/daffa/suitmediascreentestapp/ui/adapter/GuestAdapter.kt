package com.daffa.suitmediascreentestapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daffa.suitmediascreentestapp.databinding.ItemGuestBinding
import com.daffa.suitmediascreentestapp.model.entity.GuestEntity

class GuestAdapter : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

    private lateinit var onItemClick: OnItemClick
    private var listGuest = ArrayList<GuestEntity>()

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    fun setListGuest(guest: List<GuestEntity>?) {
        if (guest.isNullOrEmpty()) return
        this.listGuest.apply {
            clear()
            addAll(guest)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GuestAdapter.GuestViewHolder {
        val view = ItemGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(view)
    }

    override fun onBindViewHolder(holder: GuestAdapter.GuestViewHolder, position: Int) =
        holder.bind(guest = listGuest[position])

    override fun getItemCount(): Int = listGuest.size

    inner class GuestViewHolder(private val binding: ItemGuestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(guest: GuestEntity) {
            with(binding) {
                tvGuest.text = guest.name
                tvBirthdate.text = guest.birthdate

                itemView.setOnClickListener {
                    onItemClick.itemClick(guest)
                }
            }
        }
    }

    interface OnItemClick {
        fun itemClick(guest: GuestEntity)
    }

}
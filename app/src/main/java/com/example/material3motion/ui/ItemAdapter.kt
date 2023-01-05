package com.example.material3motion.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.material3motion.MainActivity
import com.example.material3motion.R
import com.example.material3motion.databinding.ItemLayoutBinding

class ItemAdapter(
    private val list: List<String>,
    private val listener: ClickListener
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(
        private val binding: ItemLayoutBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String, position: Int) {
            val context = itemView.context
            binding.card.transitionName = context.getString(R.string.container_item_card,position)
            binding.textView.transitionName =
                context.getString(R.string.shared_elements_item_text, position)
            binding.card.setOnClickListener {
                listener.onClick(it,binding.textView.text.toString(),position)
            }
            binding.textView.text = text

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ClickListener{
        fun onClick(view :View,text: String,position: Int)
    }

}



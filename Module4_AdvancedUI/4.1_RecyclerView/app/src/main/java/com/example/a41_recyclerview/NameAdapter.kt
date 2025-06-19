package com.example.a41_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NameAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<String, NameAdapter.NameViewHolder>(DiffCallback()) {

    inner class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)

        fun bind(name: String) {
            tvName.text = name
            itemView.setOnClickListener {
                onClick(name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_name, parent, false)
        return NameViewHolder(view)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}


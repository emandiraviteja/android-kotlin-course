package com.example.a41_recyclerview

import androidx.recyclerview.widget.DiffUtil


/**Created by Raviteja Emandi on 02,July,2025 **/
class StudentDiffCallback : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem == newItem
    }
}
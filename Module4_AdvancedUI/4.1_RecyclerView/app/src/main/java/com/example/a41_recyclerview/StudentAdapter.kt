package com.example.a41_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


/**Created by Raviteja Emandi on 02,July,2025 **/
class StudentAdapter(private val onClick: (Student) -> Unit) :
    ListAdapter<Student, StudentAdapter.StudentViewHolder>(StudentDiffCallback()) {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textName = itemView.findViewById<TextView>(R.id.textName)
        private val textAge = itemView.findViewById<TextView>(R.id.textAge)
        private val textMarks = itemView.findViewById<TextView>(R.id.textMarks)

        fun bind(student: Student) {
            textName.text = student.name
            textAge.text = "Age: ${student.age}"
            textMarks.text = "Marks: ${student.marks}"

            itemView.setOnClickListener {
                onClick(student)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
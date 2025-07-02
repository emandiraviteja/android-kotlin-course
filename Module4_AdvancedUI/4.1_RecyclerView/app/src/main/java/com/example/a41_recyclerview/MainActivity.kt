package com.example.a41_recyclerview

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        studentAdapter = StudentAdapter { student ->
            Toast.makeText(this, "${student.name} clicked", Toast.LENGTH_SHORT).show()
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = studentAdapter
            setHasFixedSize(true)
        }

        val studentList = generateStudentList()
        studentAdapter.submitList(studentList)
    }

    private fun generateStudentList(): List<Student> {
        return listOf(
            Student(1, "Ravi", 20, 88),
            Student(2, "Teja", 21, 92),
            Student(3, "Kiran", 22, 78),
            Student(4, "Vamsi", 20, 85),
            Student(5, "Manoj", 23, 90),
            Student(6, "Rohit", 21, 76),
            Student(7, "Deepak", 22, 84),
            Student(8, "Akhil", 20, 95),
            Student(9, "Raj", 21, 79),
            Student(10, "Sai", 20, 83),
            Student(11, "Anil", 23, 72),
            Student(12, "Naresh", 21, 88),
            Student(13, "Ajay", 22, 91),
            Student(14, "Lokesh", 20, 87),
            Student(15, "Pavan", 21, 93),
            Student(16, "Suresh", 20, 89),
            Student(17, "Mahesh", 22, 90),
            Student(18, "Charan", 23, 82),
            Student(19, "Tarun", 20, 86),
            Student(20, "Surya", 21, 94)
        )
    }
}


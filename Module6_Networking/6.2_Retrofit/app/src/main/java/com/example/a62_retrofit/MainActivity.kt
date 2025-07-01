package com.example.a62_retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val api = RetrofitClient.instance
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var progressBar: ProgressBar
    private val userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGet: Button = findViewById(R.id.btnGetUsers)
        val btnPost: Button = findViewById(R.id.btnPostUser)
        recyclerView = findViewById(R.id.recyclerViewUsers)
        progressBar = findViewById(R.id.progressBar)

        userAdapter = UserAdapter(userList) { user ->
            Toast.makeText(this, "Clicked: ${user.name}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userAdapter

        btnGet.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            api.getUsers().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    progressBar.visibility = View.GONE
                    if (response.isSuccessful) {
                        userList.clear()
                        response.body()?.let { userList.addAll(it) }
                        userAdapter.notifyDataSetChanged()
                        Toast.makeText(this@MainActivity, "GET successful", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Log.e("GET Error", t.message ?: "Error")
                    Toast.makeText(this@MainActivity, "GET failed", Toast.LENGTH_SHORT).show()
                }
            })
        }

        btnPost.setOnClickListener {
            val user = User(0, "Teja", "teja@email.com")
            api.createUser(user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val created = response.body()
                        Toast.makeText(this@MainActivity, "POST: ${created?.name}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("POST Error", t.message ?: "Error")
                }
            })
        }
    }
}
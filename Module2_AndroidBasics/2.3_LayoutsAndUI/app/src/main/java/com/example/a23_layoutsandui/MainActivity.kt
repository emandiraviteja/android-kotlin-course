package com.example.a23_layoutsandui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val textView = findViewById<TextView>(R.id.textView)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val switchBtn = findViewById<Switch>(R.id.switch1)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val spinnerItems = listOf("India", "USA", "Germany")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SimpleAdapter(listOf("Item 1", "Item 2", "Item 3"))

        button.setOnClickListener {
            val input = editText.text.toString()
            val selectedRadioId = radioGroup.checkedRadioButtonId
            val selectedRadio = findViewById<RadioButton>(selectedRadioId)?.text
            textView.text = "Input: $input\nChecked: ${checkBox.isChecked}\nRadio: $selectedRadio\nSeek: ${seekBar.progress}\nRating: ${ratingBar.rating}\nSwitch: ${switchBtn.isChecked}\nSpinner: ${spinner.selectedItem}"
        }
    }
}

/* SimpleAdapter.kt */
class SimpleAdapter(private val items: List<String>) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
    class ViewHolder(view: TextView) : RecyclerView.ViewHolder(view) {
        val text: TextView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TextView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(20, 20, 20, 20)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = items[position]
    }

    override fun getItemCount(): Int = items.size
}

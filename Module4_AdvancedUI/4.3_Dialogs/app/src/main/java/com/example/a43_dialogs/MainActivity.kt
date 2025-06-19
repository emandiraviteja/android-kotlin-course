package com.example.a43_dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnAlert).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Do you want to proceed?")
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(this, "Proceeded", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No", null)
                .show()
        }

        findViewById<Button>(R.id.btnTime).setOnClickListener {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val min = c.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, h, m ->
                Toast.makeText(this, "Selected: $h:$m", Toast.LENGTH_SHORT).show()
            }, hour, min, true).show()
        }

        findViewById<Button>(R.id.btnDate).setOnClickListener {
            val c = Calendar.getInstance()
            val y = c.get(Calendar.YEAR)
            val m = c.get(Calendar.MONTH)
            val d = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, year, month, day ->
                Toast.makeText(this, "Selected: $day/${month + 1}/$year", Toast.LENGTH_SHORT).show()
            }, y, m, d).show()
        }

        findViewById<Button>(R.id.btnBottomSheet).setOnClickListener {
            val view = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()

            val btn = view.findViewById<Button>(R.id.btnBottomOption)
            btn.setOnClickListener {
                Toast.makeText(this, "Bottom Sheet Action clicked", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }
    }
}

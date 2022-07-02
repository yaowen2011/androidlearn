package com.example.demo001

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        // find this tv
        tvSelectedDate = findViewById(R.id.textSelectedDate)

        btnDatePicker.setOnClickListener {
            handleClickOfBtnDatePicker()
        }
    }

    fun handleClickOfBtnDatePicker() {
        //
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            { view, selectedYear, selectedMonth, selectedDay ->

                // just a way to print data to observe
                Toast.makeText(this, "The day you choose is $selectedYear/${selectedMonth+1}/$selectedDay", Toast.LENGTH_LONG)
                    .show()

                // set the selected Calendar date in Textview
                tvSelectedDate?.text = "$selectedYear/${selectedMonth+1}/$selectedDay"
            },
            year,
            month,
            day
        ).show()


    }
}
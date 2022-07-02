package com.example.demo001

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null

    private var tvHours: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        // find this tv
        tvSelectedDate = findViewById(R.id.textSelectedDate)

        tvHours = findViewById(R.id.textHours)

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


                // calculate the difference in time from birth date till now
                val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.CHINESE)
                val theDate = sdf.parse(selectedDate)

                val selectedDateInHours = theDate.time / 60000 /60

                val currDate = sdf.parse(sdf.format(System.currentTimeMillis())).time/ 60000/ 60

                val diffHours = currDate - selectedDateInHours

                tvHours?.text = "${diffHours}h"


            },
            year,
            month,
            day
        ).show()


    }
}
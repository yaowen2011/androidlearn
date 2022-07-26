package com.example.demo001

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btn_start)
        // check name is empty
        val etName: AppCompatEditText = findViewById(R.id.et_name)

        btnStart.setOnClickListener {
            if (etName.text.toString().isEmpty()) {
                Toast.makeText(this, "Please input your name", Toast.LENGTH_LONG)
                    .show()
            } else {
                val intent = Intent(this, QuizQuestionActivity::class.java)
                startActivity(intent)
            }
        }
    }


}
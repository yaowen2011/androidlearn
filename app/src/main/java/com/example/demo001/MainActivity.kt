package com.example.demo001

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvOutput: TextView? = null

//    private var currExpression: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOutput = findViewById(R.id.tvOutput)
    }

    fun onDigit(view: View) {
        tvOutput?.append((view as Button).text)
    }

    fun onOperator(view: View) {
        val currStr = tvOutput?.text
        print(currStr)
        currStr?.let {
            val lastChar = currStr[currStr.length - 1]
            print(lastChar)
            if (lastChar == '-' || lastChar== '*' || lastChar=='+' || lastChar=='/') {

            } else {
                tvOutput?.append((view as Button).text)
            }
        }
    }

    fun onEqual(view: View) {
        var currStr = tvOutput?.text
        currStr?.let {

            val lastChar = currStr[currStr.length - 1]
            if (Character.isDigit(lastChar)) {
                if (currStr.contains("[-*+/]".toRegex())) {
                    if (currStr.contains('+')) {
                        val numArr = currStr.split('+')
                        tvOutput?.text = (numArr[0].toDouble() + numArr[1].toDouble()).toString()
                    } else if(currStr.contains('-')) {
                        val numArr = currStr.split('-')
                        tvOutput?.text = (numArr[0].toDouble() - numArr[1].toDouble()).toString()
                    } else if(currStr.contains('*')) {
                        val numArr = currStr.split('*')
                        tvOutput?.text = (numArr[0].toDouble() * numArr[1].toDouble()).toString()
                    }else if(currStr.contains('/')) {
                        val numArr = currStr.split('/')
                        tvOutput?.text = (numArr[0].toDouble() / numArr[1].toDouble()).toString()
                    }
                }
            }
        }
    }

    fun onClear(view: View) {
        tvOutput?.text = ""
    }

}
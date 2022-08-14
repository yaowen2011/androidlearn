package com.example.demo001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private var mUserName:String? = null
    private var mTotalQuestions:Int = 0
    private var mCorrectQuestions:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvUserName = findViewById<TextView>(R.id.tv_user_name)
        val tvRankInfo = findViewById<TextView>(R.id.tv_rank_info)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mTotalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        mCorrectQuestions = intent.getIntExtra(Constants.CORRECT_QUESTIONS, 0)
        tvUserName.text = mUserName

        tvRankInfo.text = "YOu got ${mCorrectQuestions} out of ${mTotalQuestions}"


    }
}
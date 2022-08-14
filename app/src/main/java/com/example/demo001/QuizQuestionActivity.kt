package com.example.demo001

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 0
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mUserName: String? = null
    private var mCorrectCount: Int = 0

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)

        ivImage = findViewById(R.id.iv_image)

        tvQuestion = findViewById(R.id.tv_question)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        // add listener
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionsList = Constants.getQuestions()

        setQuestion()
//        defaultOptionsView()

    }

    private fun setQuestion() {

        defaultOptionsView()
        val question: Question = mQuestionsList!![mCurrentPosition]
        progressBar?.progress = mCurrentPosition + 1
        tvProgress?.text = "${progressBar?.progress}/${progressBar?.max}"
        tvQuestion?.text = question.question

        ivImage?.setImageResource(question.image)
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if (mCurrentPosition == mQuestionsList!!.size - 1) {
            btnSubmit?.text = "Finish"
        } else {
            btnSubmit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }

        for (option in options) {
//            option.setTextColor(Color.parseColor("#878700"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun answerView(answer:Int, drawableView: Int) {
        when(answer) {
            1-> {
                tvOptionOne?.background = ContextCompat.getDrawable(this, drawableView)
            }
            2-> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }
            3-> {
                tvOptionThree?.background = ContextCompat.getDrawable(this, drawableView)
            }
            4-> {
                tvOptionFour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.btn_submit-> {
                if(mSelectedOptionPosition ==0) {
                    // do nothing and show next question
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionsList!!.size-1 -> {
                            setQuestion()
                        }
                        else -> {
//                            Toast.makeText(this, "You made it to the end", Toast.LENGTH_LONG)
//                                .show()
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                            intent.putExtra(Constants.CORRECT_QUESTIONS, mCorrectCount)
                            startActivity(intent)
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition)
                    if (mSelectedOptionPosition == question?.correctAnswer) {
                        answerView(mSelectedOptionPosition, R.drawable.correct_option_border_bg)
                        mCorrectCount++
                    } else {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                        answerView(question!!.correctAnswer, R.drawable.correct_option_border_bg)
                    }

                    if(mCurrentPosition == mQuestionsList!!.size-1) {
                        btnSubmit?.text = "FINISH"
                    } else {
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }
}
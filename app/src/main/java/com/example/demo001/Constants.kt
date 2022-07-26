package com.example.demo001

object Constants {
    fun getQuestions(): ArrayList<Question> {
        var questionList = ArrayList<Question>()
        //question 1
        val que1 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentian", "Austrialia", "country3", "China",
            1
        )

        questionList.add(que1)
        val que2 = Question(
            2, "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "denmark", "Austrialia", "country3", "China",
            1
        )

        questionList.add(que2)

        return questionList
    }
}
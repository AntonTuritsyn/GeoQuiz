package com.bignerdranch.android.geomain.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView
//    private lateinit var resultTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this)[QuizViewModel::class.java]
    }

//    private var result = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle? called)")
        setContentView(R.layout.activity_main)                                                              // в () идентификатор ресурса для обращения к ресурсу activity_main

//        val provider: ViewModelProvider = ViewModelProviders.of(this)                                     // устарело
       /* val provider = ViewModelProvider(this)                            // теперь так
        val quizViewModel = provider[QuizViewModel::class.java]             // лениво инициализировали данное свойство выше
        Log.d(TAG, "Got a QuizModel: $quizViewModel")*/

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)
//        resultTextView = findViewById(R.id.result_text_view)


        trueButton.setOnClickListener {
            // какое-то действие после нажатия. Слушатель сообщает о нажатии кнопки
            /*Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT
            ).show()*/
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            /*Toast.makeText(
                this,
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT
            ).show()*/
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            /*val questionTextResId = questionBank[currentIndex].textResId                                  // объединяем в функцию
        questionTextView.setText(questionTextResId)*/
            isAnswered(quizViewModel.currentIndex)
            updateQuestion()
        }

        prevButton.setOnClickListener {
            quizViewModel.moveToPrev()
            /*currentIndex = when (currentIndex) {
                in 1..questionBank.size -> currentIndex - 1
                else -> questionBank.size - 1
            }*/
            isAnswered(quizViewModel.currentIndex)
            updateQuestion()
        }

        questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
//            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        /*val questionTextResId = questionBank[currentIndex].textResId                                      // !!!!! уточнить, как работает!!!!! понял    // объединяем в функцию
        questionTextView.setText(questionTextResId)*/
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
//        val questionTextResId = questionBank[currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
//        val correctAnswer = questionBank[currentIndex].answer
        val correctAnswer = quizViewModel.currentQuestionAnswer

        trueButton.isEnabled = false
        falseButton.isEnabled = false
        quizViewModel.answeredQuestion = true

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
//            result++
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun isAnswered(index: Int) {
        val isQuestionAnswered = quizViewModel.ans(index)
        trueButton.isEnabled = !isQuestionAnswered
        falseButton.isEnabled = !isQuestionAnswered
    }
}
package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView(){

        //set layout + adapter
        binding.rvQuestions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter

        //Adds questions
        questions.add(Question(getString(R.string.valVar), false))
        questions.add(Question(getString(R.string.ECTS), false))
        questions.add(Question(getString(R.string.unit), true))
        questions.add(Question(getString(R.string.whenSwitch), true))

    }
}
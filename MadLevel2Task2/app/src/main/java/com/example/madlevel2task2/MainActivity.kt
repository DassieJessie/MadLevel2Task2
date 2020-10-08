package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

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

        questionSwipe().attachToRecyclerView(rvQuestions)
    }


    private fun questionSwipe(): ItemTouchHelper {

        val itemTouchHelperCallback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                //false because dragDirs = 0
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                when (direction) {
                    ItemTouchHelper.RIGHT ->
                        checkAnswer(position, false)

                    ItemTouchHelper.LEFT ->
                        checkAnswer(position, true)
                }
                //zonder crashed de app wanneer je een item wilt deleten
                questionAdapter.notifyDataSetChanged()
            }
        }

        return ItemTouchHelper(itemTouchHelperCallback)
    }

    private fun checkAnswer(position: Int, correct: Boolean){

        if(questions[position].answer == correct){
            questions.removeAt(position)
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        } else{
            Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_LONG).show()
        }
    }
}
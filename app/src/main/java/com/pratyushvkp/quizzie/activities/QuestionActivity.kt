package com.pratyushvkp.quizzie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.pratyushvkp.quizzie.R
import com.pratyushvkp.quizzie.adapters.OptionAdapter
import com.pratyushvkp.quizzie.models.Question
import com.pratyushvkp.quizzie.models.Quiz
import com.pratyushvkp.quizzie.databinding.ActivityQuestionBinding


class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding
    var quizzes : MutableList<Quiz>? = null
    var questions: MutableMap<String,Question>? = null
    var index = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpFirestore()
        setUpEventListner()
    }

    private fun setUpEventListner() {
        binding.btnPrevious.setOnClickListener {
            index--
            bindViews()
        }
        binding.btnNext.setOnClickListener {
            index++
            bindViews()
        }
        binding.btnSubmit.setOnClickListener {
            Log.d("finalquiz",questions.toString())

            val intent = Intent(this,ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("quiz",json)
            startActivity(intent)

        }
    }

    private fun setUpFirestore() {
        val firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("date")
        if(date!=null){
            firestore.collection("quizzes").whereEqualTo("title",date)
                .get()
                .addOnSuccessListener {
                    if(it!=null && !it.isEmpty){
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()
                    }
                }
        }

    }

    private fun bindViews() {
    binding.btnPrevious.visibility = View.GONE
    binding.btnNext.visibility = View.GONE
    binding.btnSubmit.visibility = View.GONE

   if(index == 1){
       binding.btnNext.visibility = View.VISIBLE
   }
        else if(index == questions!!.size){
            binding.btnSubmit.visibility = View.VISIBLE
        binding.btnPrevious.visibility = View.VISIBLE
        }
        else{
            binding.btnPrevious.visibility = View.VISIBLE
          binding.btnNext.visibility = View.VISIBLE
        }

        val question = questions!!["question$index"]
        question?.let {
            val optionListRecyclerView = findViewById<RecyclerView>(R.id.optionListRecyclerView)
            val description = findViewById<TextView>(R.id.description)
            description.text = question.description
            val optionAdapter = OptionAdapter(this,question)
            optionListRecyclerView.layoutManager = LinearLayoutManager(this)
            optionListRecyclerView.adapter = optionAdapter
            optionListRecyclerView.setHasFixedSize(true)
        }



    }
}
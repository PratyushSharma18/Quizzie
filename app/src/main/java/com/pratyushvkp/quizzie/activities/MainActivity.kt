package com.pratyushvkp.quizzie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.pratyushvkp.quizzie.R
import com.pratyushvkp.quizzie.adapters.QuizAdapter
import com.pratyushvkp.quizzie.models.Quiz
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    lateinit var firestore: FirebaseFirestore
    private var quizList = mutableListOf<Quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViews()
    }

    private fun populateDummyData() {
        quizList.add(Quiz("12-06-2023","12-06-2023"))
        quizList.add(Quiz("13-06-2023","13-06-2023"))
        quizList.add(Quiz("14-06-2023","14-06-2023"))
        quizList.add(Quiz("15-06-2023","15-06-2023"))
        quizList.add(Quiz("16-06-2023","16-06-2023"))
        quizList.add(Quiz("17-06-2023","17-06-2023"))
        quizList.add(Quiz("18-06-2023","18-06-2023"))
        quizList.add(Quiz("19-06-2023","19-06-2023"))
        quizList.add(Quiz("20-06-2023","20-06-2023"))
        quizList.add(Quiz("21-06-2023","21-06-2023"))
        quizList.add(Quiz("22-06-2023","22-06-2023"))
        quizList.add(Quiz("23-06-2023","23-06-2023"))
    }

    private fun setUpViews() {
        setUpFireStore()
      setUpDrawerLayout()
        setUpRecyclerView()
        setUpDatePicker()

    }

    private fun setUpDatePicker() {
        val btn_datepicker = findViewById<FloatingActionButton>(R.id.btn_datepicker)
        btn_datepicker.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"datepicker")
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER",datePicker.headerText)
                val dateFormatter = SimpleDateFormat("dd-mm-yyyy")
                val date = dateFormatter.format(Date(it))
                val intent = Intent(this,QuestionActivity::class.java)
                intent.putExtra("date",date)
                startActivity(intent)

            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER",datePicker.headerText)
            }
            datePicker.addOnCancelListener {
                Log.d("DATEPICKER","Date picker cancelled")
            }

        }
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value==null || error != null){
                Toast.makeText(this,"Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        val quizRecyclerView = findViewById<RecyclerView>(R.id.quizRecyclerView)
       adapter = QuizAdapter(this, quizList)
        quizRecyclerView.layoutManager = GridLayoutManager(this,2)
        quizRecyclerView.adapter = adapter
    }

    private fun setUpDrawerLayout(){
     val appBar = findViewById<MaterialToolbar>(R.id.appBar)
     setSupportActionBar(appBar)
     val mainDrawer = findViewById<DrawerLayout>(R.id.mainDrawer)
     actionBarDrawerToggle = ActionBarDrawerToggle(this,mainDrawer,
         R.string.app_name,
         R.string.app_name
     )
     actionBarDrawerToggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener {
            val intent = Intent(this,ProfileActivity::class.java)
            startActivity(intent)
            mainDrawer.closeDrawers()
            true
        }
 }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
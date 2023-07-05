package com.pratyushvkp.quizzie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.pratyushvkp.quizzie.R

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)

       val auth = FirebaseAuth.getInstance()
        if(auth.currentUser!=null){
            Toast.makeText(this,"User is already logged in" , Toast.LENGTH_SHORT).show()
            redirect("Main")
        }


        val clickToStart = findViewById<TextView>(R.id.clickToStart)
        clickToStart.setOnClickListener {

           redirect("Login")
        }
    }

    private fun redirect(name: String) {
        val intent = when(name){


        "Login" -> Intent(this, LoginActivity::class.java)
        "Main" -> Intent(this, MainActivity::class.java)

            else -> throw Exception("No path exists")
        }
        startActivity(intent)
        finish()
    }
}
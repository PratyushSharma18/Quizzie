package com.pratyushvkp.quizzie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.pratyushvkp.quizzie.R

class LoginActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        val btnLogIn = findViewById<Button>(R.id.btnLogIn)
        btnLogIn.setOnClickListener {
            LogInUser()
        }
        val btnSignUp = findViewById<TextView>(R.id.btnSignup)
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun LogInUser(){
        val EmailAddress: EditText = findViewById(R.id.EmailAddress)
        val email = EmailAddress.text.toString()

        val Password: EditText = findViewById(R.id.Password)
        val password = Password.text.toString()


        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this,"Email and Password can't be empty", Toast.LENGTH_SHORT).show()
            return
        }


        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if(it.isSuccessful){
                    Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Authentication Failed",Toast.LENGTH_SHORT).show()
                }
            }
    }
}
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

class SignUpActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth = FirebaseAuth.getInstance()

        val btnSignUp = findViewById<Button>(R.id.btnSignup)
        btnSignUp.setOnClickListener {
            signUpUser()
        }

        val backLogin = findViewById<TextView>(R.id.backLogin)
        backLogin.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun signUpUser(){
         val SEmailAddress: EditText = findViewById(R.id.SEmailAddress)
         val email = SEmailAddress.text.toString()

        val SPassword: EditText = findViewById(R.id.SPassword)
        val password = SPassword.text.toString()

        val SConfirmPassword:EditText= findViewById(R.id.SConfirmPassword)
        val confirmPassword = SConfirmPassword.text.toString()

        if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            Toast.makeText(this,"Email and Password can't be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if(password!=confirmPassword){
            Toast.makeText(this,"Password and Confirm Password don,t match" , Toast.LENGTH_SHORT).show()
            return
        }


        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if(it.isSuccessful){
                  Toast.makeText(this,"Created Successfully",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Error creating user",Toast.LENGTH_SHORT).show()
                }
            }

        }





    }

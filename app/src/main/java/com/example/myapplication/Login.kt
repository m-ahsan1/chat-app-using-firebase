package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //fire auth
        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.username)
        edtPass = findViewById(R.id.password)
        btnLogin = findViewById(R.id.login)
        btnSignup = findViewById(R.id.signup)

        btnSignup.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)

        }
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPass.text.toString()

            login(email, password)

        }


    }

    private fun login(email:String, password:String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Login, "User does not exist.", Toast.LENGTH_SHORT).show()
                }
            }

    }

}
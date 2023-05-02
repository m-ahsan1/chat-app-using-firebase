package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPass: EditText
    private lateinit var btnSignup: Button
    private lateinit var mAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        edtName = findViewById(R.id.fname)
        edtEmail = findViewById(R.id.email)
        edtPass = findViewById(R.id.password)
        btnSignup = findViewById(R.id.sign_up)

        btnSignup.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPass.text.toString()

            signUp(email, password)
        }
    }

    private fun signUp(email:String, password:String){
        //code for signup

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent (this@SignUp, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@SignUp, "Error : Cannot create user.", Toast.LENGTH_SHORT).show()
                }
            }

    }

}
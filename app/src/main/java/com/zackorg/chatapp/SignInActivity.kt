package com.zackorg.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPass: EditText
    private lateinit var btnSignup: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mAuth= FirebaseAuth.getInstance()

        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edtEmail)
        edtPass = findViewById(R.id.edtPass)
        btnSignup = findViewById(R.id.btnSignup)

        btnSignup.setOnClickListener {
            val email= edtEmail.text.toString()
            val password = edtPass.text.toString()

            signup(email,password)
        }
    }

    private fun signup(email:String,password:String){
        //signup activity

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@SignInActivity,MainActivity::class.java)
                    startActivity(intent)

                    // Sign in success, update UI with the signed-in user's information
                    // Log.d(TAG, "createUserWithEmail:success")
                    // val user = auth.currentUser
                    // updateUI(user)
                } else {
                    Toast.makeText(this@SignInActivity,"Some Error Occurred",Toast.LENGTH_SHORT).show()

                    // If sign in fails, display a message to the user.
                    // Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    // Toast.makeText(baseContext, "Authentication failed.",
                    // Toast.LENGTH_SHORT).show()
                    // updateUI(null)
                }
            }

    }
}
package com.zackorg.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.net.PasswordAuthentication

class LogInActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPass:EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        mAuth= FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edtEmail)
        edtPass = findViewById(R.id.edtPass)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnSignup)

        btnSignup.setOnClickListener {
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPass.text.toString()

            login(email,password)
        }
    }

    private fun login(email:String,  password:String){
        //login activity
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LogInActivity,MainActivity::class.java)
                    startActivity(intent)

                    // Sign in success, update UI with the signed-in user's information
                   /* Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)*/
                } else {
                    Toast.makeText(this@LogInActivity,"User Does Not Exist",Toast.LENGTH_SHORT).show()
                    // If sign in fails, display a message to the user.
                   /* Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)*/
                }
            }

    }
}
package com.job.istation


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.job.istation.commoners.Tools
import kotlinx.android.synthetic.main.activity_signup.*

class RegisterActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, RegisterActivity::class.java)
    }

    private var parent_view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        parent_view = findViewById(android.R.id.content)

        Tools.setSystemBarColor(this, android.R.color.white)
        Tools.setSystemBarLight(this)
    }

    fun toMainActivity(v: View) {

        // set authentication of user with database

        btnRegister.setOnClickListener {
            val email = email_editTextRegister.text.toString()
            val password = password_editTextRegister.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please Enter Email & Password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (!it.isSuccessful) return@addOnCompleteListener

                        //elseif successful
                        Toast.makeText(this, "User created successfully", Toast.LENGTH_LONG).show()
                        startActivity(MainActivity.newIntent(this))

                    }
                    .addOnFailureListener {
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                    }
            }
        }
    }
}

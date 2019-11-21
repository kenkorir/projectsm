package com.job.istation


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.job.istation.commoners.Tools
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, LoginActivity::class.java)
    }

    private var parent_view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        parent_view = findViewById(android.R.id.content)

        Tools.setSystemBarColor(this, android.R.color.white)
        Tools.setSystemBarLight(this)

        (findViewById(R.id.forgot_password) as View).setOnClickListener {
            Snackbar.make(
                parent_view!!,
                "Sorry mate! create a new account.",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        (findViewById(R.id.sign_up) as View).setOnClickListener {

            startActivity(RegisterActivity.newIntent(this@LoginActivity))
        }
    }

    fun toRegisterActivity(v: View) {
        startActivity(RegisterActivity.newIntent(this))
    }

    fun toMainActivity(v: View) {

        //set authentication for access into the app

        btn_Login.setOnClickListener {
            val emailLogin = email_editTextLogin.text.toString()
            val passwordLogin = password_edtTextLogin.text.toString()

            if (emailLogin.isEmpty() || passwordLogin.isEmpty()) {
                Toast.makeText(this, "Please enter Email and Password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailLogin, passwordLogin)
                    .addOnCompleteListener {
                        if (!it.isSuccessful) {
                            Toast.makeText(this, "Error Logging in", Toast.LENGTH_LONG).show()
                            return@addOnCompleteListener
                        }
                        startActivity(MainActivity.newIntent(this))

                    }
                    .addOnFailureListener {
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()

                    }
            }


        }
    }
}

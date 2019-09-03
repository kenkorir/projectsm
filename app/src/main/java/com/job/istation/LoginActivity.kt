package com.job.istation


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

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
                "Forgot Password",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        (findViewById(R.id.sign_up) as View).setOnClickListener {
            Snackbar.make(
                parent_view!!,
                "Sign Up",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}

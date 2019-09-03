package com.job.istation


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private var parent_view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        parent_view = findViewById(android.R.id.content)

        Tools.setSystemBarColor(this, android.R.color.white)
        Tools.setSystemBarLight(this)

    }
}

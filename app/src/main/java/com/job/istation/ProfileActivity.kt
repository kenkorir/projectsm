package com.job.istation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.job.istation.commoners.Tools

class ProfileActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, ProfileActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Tools.setSystemBarColor(this, R.color.purple_A700)
    }

    fun toSaleActivity(v: View){
        startActivity(SalesActivity.newIntent(this))
    }

    fun toStuff(v:View){
        startActivity(StuffActivity.newIntent(this))
    }

    fun toreviews(v:View){
        Snackbar.make(
            findViewById(android.R.id.content)!!,
            "Hold up! No reviews available yet",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}

package com.job.istation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.job.istation.commoners.Tools
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    fun toSaleActivity(v: View){
        startActivity(SalesActivity.newIntent(this))
    }

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
    }

    private fun initToolbar() {

        setSupportActionBar(toolbar)
        Tools.setSystemBarColor(this, R.color.purple_A700)
        Tools.setSystemBarLight(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        Tools.changeMenuIconColor(menu, resources.getColor(R.color.grey_60))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            finish()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun toProfile(v: View){
        startActivity(ProfileActivity.newIntent(this))
    }
}

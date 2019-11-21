package com.job.istation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_data.*


class EnterDataActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, EnterDataActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        setSupportActionBar(toolbar)

        //initialize firestore
        db = FirebaseFirestore.getInstance()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_done, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.done -> {

                //created method to enter data into firestore db
                store()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun store() {
        val fuel = findViewById<View>(R.id.fuelType) as TextInputEditText
        val amnt = findViewById<View>(R.id.amount) as TextInputEditText
        val staff = findViewById<View>(R.id.staff) as TextInputEditText
        val price = findViewById<View>(R.id.price) as TextInputEditText

        val Fuel = fuel.text.toString().trim()
        val Amount = amnt.text.toString().trim()
        val Staff = staff.text.toString().trim()
        val Price = price.text.toString().trim()

        if (!Fuel.isEmpty() && !Amount.isEmpty() && !Staff.isEmpty() && !Price.isEmpty()) {

            try {

                val items = HashMap<String, Any>()
                items.put("fuel", Fuel)
                items.put("amount", Amount)
                items.put("Staff", Staff)
                items.put("Price", Price)

                db.collection("Fuel").document().set(items).addOnSuccessListener { void: Void? ->
                    Toast.makeText(this, "Successfully uploded  to the database", Toast.LENGTH_LONG)
                        .show()
                    finish()
                }
                    .addOnFailureListener { exception: Exception ->
                        Toast.makeText(this, exception.toString(), Toast.LENGTH_LONG).show()
                    }

            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(this, "Please Fill up the fields!", Toast.LENGTH_SHORT).show()
        }
    }
}

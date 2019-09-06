package com.job.istation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.job.istation.commoners.AdapterListBasic
import com.job.istation.commoners.Tools
import com.job.istation.model.DataGenerator
import kotlinx.android.synthetic.main.activity_stuff.*


class StuffActivity : AppCompatActivity() {

    private var parent_view: View? = null

    private var mAdapter: AdapterListBasic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stuff)
        parent_view = findViewById<View>(android.R.id.content)
        Tools.setSystemBarColor(this, R.color.purple_A700)

        initToolbar()
        initComponent()
    }

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, StuffActivity::class.java)
    }

    private fun initToolbar() {
        Tools.setSystemBarColor(this, R.color.purple_A700)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Tools.setSystemBarColor(this)
    }

    private fun initComponent() {
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.setHasFixedSize(true)

        val items = DataGenerator.getPeopleData(this)
        items.addAll(DataGenerator.getPeopleData(this))
        items.addAll(DataGenerator.getPeopleData(this))

        //set data and list adapter
        mAdapter = AdapterListBasic(this, items)
        recyclerView!!.adapter = mAdapter

        // on item list clicked
        mAdapter!!.setOnItemClickListener { _, obj, _ ->
            Snackbar.make(parent_view!!, "Item " + obj.name + " clicked", Snackbar.LENGTH_SHORT).show() }

    }

}

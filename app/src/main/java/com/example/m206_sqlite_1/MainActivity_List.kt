package com.example.m206_sqlite_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity_List : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        val listView = findViewById<ListView>(R.id.listview)

        val db = BDAppart(applicationContext)

        val AppartList = db.getAll()

        val StringList = ArrayList<String>()

        for(app in AppartList){

            StringList.add(app.toString())

        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, StringList)

        listView.adapter = adapter
    }
}
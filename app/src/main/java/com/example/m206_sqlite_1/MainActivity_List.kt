package com.example.m206_sqlite_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

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

        listView.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->

                val selectedAppart = AppartList[position]

                val i = Intent(this,MainActivity_MAJ::class.java)

                i.putExtra("id", selectedAppart.id)
                i.putExtra("offre", selectedAppart.offre)
                i.putExtra("surface", selectedAppart.surface)
                i.putExtra("avecPar", selectedAppart.avecParking)
                i.putExtra("image", selectedAppart.image)

                startActivity(i)



        }


        listView.setOnItemLongClickListener { parent, view, position, id ->
            val selectedAppart = AppartList[position]
            val db = BDAppart(applicationContext)
            if(db.deleteAppart(selectedAppart.id) > 0) {
                Toast.makeText(applicationContext,"Succesfuly Deleted", Toast.LENGTH_LONG).show()
            }

            else {


                Toast.makeText(applicationContext,"Failure", Toast.LENGTH_LONG).show()
            }

            refreshActivity()

            true // Consume the event, return true
        }





    }

    override fun onResume() {
        super.onResume()

        refreshActivity()
    }

    private fun refreshActivity() {

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
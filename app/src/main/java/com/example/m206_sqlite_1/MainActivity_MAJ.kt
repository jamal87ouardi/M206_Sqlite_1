package com.example.m206_sqlite_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity_MAJ : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_maj)

        var Id_edit = findViewById<EditText>(R.id.ID_update)
        val Offre = findViewById<EditText>(R.id.Offre_up)
        val Surface = findViewById<EditText>(R.id.Surface_up)
        val Parking = findViewById<EditText>(R.id.avecParking_up)
        val Image = findViewById<EditText>(R.id.image_up)


        Id_edit.setText(intent.getIntExtra("id",-1).toString())
        //Id.inputType = android.text.InputType.TYPE_NULL

        Offre.setText(intent.getStringExtra("offre"))
        Surface.setText(intent.getIntExtra("surface",-1).toString())
        Parking.setText(intent.getIntExtra("avecPar",-1).toString())
        Image.setText(intent.getStringExtra("image"))

        val btnUpdate = findViewById<Button>(R.id.Update)

        btnUpdate.setOnClickListener {

            val id = intent.getIntExtra("id",-1)
            val newOffre = Offre.text.toString()
            val newSurface = Surface.text.toString().toInt()
            val newParking = Parking.text.toString().toInt()
            val newImage = Image.text.toString()

            val db = BDAppart(applicationContext)

            val resultat = db.updateAppart(id,newOffre,newSurface,newParking, newImage)

            if(resultat > 0){

                Toast.makeText(applicationContext,"Succesfuly Updated", Toast.LENGTH_LONG).show()
            }
            else {

                Toast.makeText(applicationContext,"Failure",Toast.LENGTH_LONG).show()

            }


        }


    }
}
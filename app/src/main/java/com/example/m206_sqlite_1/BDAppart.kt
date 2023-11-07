package com.example.m206_sqlite_1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDAppart(context : Context) : SQLiteOpenHelper(context,"BD_App",null,1) {
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE Appart_Table (id INTEGER PRIMARY KEY, offre TEXT, surface INTEGER, avecParking INTEGER, image TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Appartement")
        onCreate(db)
    }

    fun ajouterAppart(appart : Appartement) {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", appart.id)
        values.put("offre", appart.offre)
        values.put("surface", appart.surface)
        values.put("avecParking", appart.avecParking)
        values.put("image", appart.image)
        db.insert("Appart_Table", null, values)
        db.close()

    }



}
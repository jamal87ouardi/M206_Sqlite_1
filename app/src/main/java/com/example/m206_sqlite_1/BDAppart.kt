package com.example.m206_sqlite_1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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
    fun ajouterAppart(appart : Appartement):Long {

        val db = this.writableDatabase
        val values = ContentValues()

        values.put("id", appart.id)
        values.put("offre", appart.offre)
        values.put("surface", appart.surface)
        values.put("avecParking", appart.avecParking)
        values.put("image", appart.image)

        val inserted = db.insert("Appart_Table", null, values)
        db.close()
        return inserted
    }

    fun getAll():ArrayList<Appartement> {

        var AppartList = ArrayList<Appartement>()

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Appart_Table", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val offre = cursor.getString(1)
                val surface = cursor.getInt(2)
                val avecParking = cursor.getInt(3)
                val image = cursor.getString(4)

                val app = Appartement(id,offre,surface,avecParking,image)

                AppartList.add(app)

            } while (cursor.moveToNext())
        }

        db.close()
        return AppartList

    }

    fun updateAppart(id : Int, newOffre:String, newSurface : Int, newAvecParking : Int, newImage : String):Int{
        val db = this.writableDatabase
        val values = ContentValues()


        values.put("offre", newOffre)
        values.put("surface", newSurface)
        values.put("avecParking", newAvecParking)
        values.put("image", newImage)

        val whereClause = "id = ?"
        val whereArgs = arrayOf(id.toString())

        // Perform the update
        return db.update("Appart_Table", values, whereClause, whereArgs)

    }

    fun deleteAppart(id:Int):Int {
        val db = writableDatabase
        val whereClause = "id = ?"
        val whereArgs = arrayOf(id.toString())
        val deletedRows = db.delete("Appart_Table", whereClause, whereArgs)
        db.close()
        return deletedRows
    }



}
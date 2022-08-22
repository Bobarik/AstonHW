package com.gmail.vlaskorobogatov.astonhw.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?): SQLiteOpenHelper(context, "contacts", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS CONTACTS(id INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, phoneNumber TEXT, img TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}
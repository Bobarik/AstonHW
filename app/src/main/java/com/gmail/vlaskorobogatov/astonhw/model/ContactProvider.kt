package com.gmail.vlaskorobogatov.astonhw.model

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class ContactProvider : ContentProvider() {

    companion object {
        const val PROVIDER_NAME = "com.gmail.vlaskorobogatov.astonhw.model/ContactProvider"
        const val URL = "content://$PROVIDER_NAME/CONTACTS"
        val CONTENT_URI = Uri.parse(URL)

        const val TABLENAME = "CONTACTS"
        val ID = "id"
        val FIRSTNAME = "firstName"
        val LASTNAME = "lastName"
        val PHONE = "phoneNumber"
        val IMG = "img"
    }

    lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        val helper = DbHelper(context)
        db = helper.writableDatabase
        return true
    }

    override fun query(
        uri: Uri,
        columns: Array<out String>?,
        condition: String?,
        conditionVals: Array<out String>?,
        orderBy: String?
    ): Cursor? {
        return db.query(TABLENAME, columns, condition, conditionVals, null, null, orderBy)
    }

    override fun getType(uri: Uri): String {
        return "vnd.android.cursor.dir/vnd.example.contacts"
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri {
        db.insert(TABLENAME, null, contentValues)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun delete(uri: Uri, condition: String?, conditionVals: Array<out String>?): Int {
        return db.delete(TABLENAME, condition, conditionVals)
    }

    override fun update(
        uri: Uri,
        contentValues: ContentValues?,
        condition: String?,
        conditionVals: Array<out String>?
    ): Int {
        return db.update(TABLENAME, contentValues, condition, conditionVals)
    }

}
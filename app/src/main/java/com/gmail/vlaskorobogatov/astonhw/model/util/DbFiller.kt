package com.gmail.vlaskorobogatov.astonhw.model.util

import android.content.ContentResolver
import android.content.ContentValues
import com.gmail.vlaskorobogatov.astonhw.model.Contact
import com.gmail.vlaskorobogatov.astonhw.model.ContactProvider
import kotlin.random.Random
import kotlin.random.nextInt

object DbFiller {

    private val IMGURL = "https://picsum.photos/id/???/200/200"
    private val FIRSTNAMES = listOf(
        "Melody", "Rivers", "Danna", "Hayden", "Amiya", "Craig",
        "Garrett", "Morton", "Breanna", "Lynch", "Kenya", "Sexton",
        "Alijah", "Oneal", "Salvatore", "Pennington", "Gary", "Kirk",
        "Abigail", "Shaw", "Pierre", "Lloyd", "Elsa", "Oconnor",
        "Ali", "Shields", "Emmanuel", "Erickson", "Lyric", "Stanton",
        "Grace", "Lucero", "Chelsea", "Rangel", "Maxim", "Hood",
        "Talan", "Schultz", "Michael", "Zuniga", "Janessa", "Love",
        "Braeden", "Richardson", "Angel", "Frost", "Gavin", "Mullen",
        "Ismael", "Bridges", "Kennedy", "Davies", "Keely", "Hays",
        "Alina", "Briggs", "Areli", "Harmon", "Mauricio", "Clark",
    )

    private val LASTNAMES = listOf(
        "Melody", "Rivers", "Danna", "Hayden", "Amiya", "Craig",
        "Garrett", "Morton", "Breanna", "Lynch", "Kenya", "Sexton",
        "Alijah", "Oneal", "Salvatore", "Pennington", "Gary", "Kirk",
        "Abigail", "Shaw", "Pierre", "Lloyd", "Elsa", "Oconnor",
        "Ali", "Shields", "Emmanuel", "Erickson", "Lyric", "Stanton",
        "Grace", "Lucero", "Chelsea", "Rangel", "Maxim", "Hood",
        "Talan", "Schultz", "Michael", "Zuniga", "Janessa", "Love",
        "Braeden", "Richardson", "Angel", "Frost", "Gavin", "Mullen",
        "Ismael", "Bridges", "Kennedy", "Davies", "Keely", "Hays",
        "Alina", "Briggs", "Areli", "Harmon", "Mauricio", "Clark",
    )

    fun fillDb(n: Int, contentResolver: ContentResolver) {
        for (i in 1..n) {
            val contact = Contact(
                FIRSTNAMES[Random.nextInt(FIRSTNAMES.indices)],
                LASTNAMES[Random.nextInt(LASTNAMES.indices)],
                "+" + "${Random.nextInt(0..999999999)}".padStart(9, '0'),
                IMGURL.replace("???", Random.nextInt(0..1000).toString())
            )
            val content = ContentValues()
            content.put(ContactProvider.FIRSTNAME, contact.firstName)
            content.put(ContactProvider.LASTNAME, contact.lastName)
            content.put(ContactProvider.IMG, contact.imgUrl)
            content.put(ContactProvider.PHONE, contact.phoneNumber)
            contentResolver.insert(ContactProvider.CONTENT_URI, content)
        }
    }
}
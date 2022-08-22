package com.gmail.vlaskorobogatov.astonhw.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val imgUrl: String
) : Parcelable
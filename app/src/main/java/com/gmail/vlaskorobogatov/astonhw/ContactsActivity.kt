package com.gmail.vlaskorobogatov.astonhw

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.vlaskorobogatov.astonhw.databinding.ActivityContactsBinding

class ContactsActivity : AppCompatActivity() {

    lateinit var binding: ActivityContactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}

package com.gmail.vlaskorobogatov.astonhw.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.gmail.vlaskorobogatov.astonhw.databinding.FragmentContactDetailsBinding
import com.gmail.vlaskorobogatov.astonhw.model.Contact

class ContactDetailsFragment : Fragment() {
    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        val contact: Contact = arguments?.get("contact") as Contact

        binding.image.load(contact.imgUrl)
        binding.firstName.text = contact.firstName
        binding.lastName.text = contact.lastName
        binding.phoneNumber.text = contact.phoneNumber

        return binding.root
    }
}
package com.gmail.vlaskorobogatov.astonhw.fragments

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.gmail.vlaskorobogatov.astonhw.databinding.FragmentContactListBinding
import com.gmail.vlaskorobogatov.astonhw.fragments.recycler_additionals.ContactAdapter
import com.gmail.vlaskorobogatov.astonhw.model.ContactProvider
import com.gmail.vlaskorobogatov.astonhw.model.util.DbFiller

class ContactListFragment : Fragment() {
    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!
    private lateinit var rs: Cursor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)

        rs = requireContext().contentResolver.query(
            ContactProvider.CONTENT_URI,
            arrayOf(
                ContactProvider.ID,
                ContactProvider.FIRSTNAME,
                ContactProvider.LASTNAME,
                ContactProvider.PHONE,
                ContactProvider.IMG
            ),
            null,
            null,
            null
        )!!

        if (!rs.moveToNext()) {
            DbFiller.fillDb(100, requireContext().contentResolver)
        }

        rs.moveToFirst()

        val adapter = ContactAdapter(this, rs)

        binding.recyclerContactList.adapter = adapter
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.recyclerContactList.addItemDecoration(divider)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rs.close()
        _binding = null
    }
}
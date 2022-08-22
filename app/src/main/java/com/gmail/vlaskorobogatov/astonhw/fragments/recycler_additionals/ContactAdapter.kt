package com.gmail.vlaskorobogatov.astonhw.fragments.recycler_additionals

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gmail.vlaskorobogatov.astonhw.R
import com.gmail.vlaskorobogatov.astonhw.databinding.ContactRecyclerViewBinding
import com.gmail.vlaskorobogatov.astonhw.fragments.ContactListFragment
import com.gmail.vlaskorobogatov.astonhw.fragments.ContactListFragmentDirections
import com.gmail.vlaskorobogatov.astonhw.model.Contact

class ContactAdapter(
    private val context: ContactListFragment,
    private var cursor: Cursor
) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(binding: ContactRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val img: ImageView = itemView.findViewById(R.id.contact_img_view)
        val name: TextView = itemView.findViewById(R.id.contact_name_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ContactRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)
        val contact = Contact(
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getString(4)
        )

        holder.img.load(cursor.getString(4))
        holder.name.text =
            context.getString(R.string.contact_name, cursor.getString(1), cursor.getString(2))

        holder.itemView.setOnClickListener {
            val action =
                ContactListFragmentDirections
                    .actionContactListFragmentToContactDetailsFragment(contact)
            context.findNavController().navigate(action)
        }

        holder.itemView.setOnLongClickListener {
            val action =
                ContactListFragmentDirections
                    .actionContactListFragmentToContactDetailsFragment(contact)
            context.findNavController().navigate(action)
            true
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return cursor.count
    }
}
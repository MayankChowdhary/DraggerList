package com.example.draggable.ui.home.DraggableAdapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.draggable.R
import com.example.draggable.ui.home.DraggableAdapters.ListDataItems.Companion.getImage


class RecyclerListAdapter() : RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder>() {

    private var users = emptyList<String>().toMutableList()




    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ItemViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)

    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textview:TextView = itemView.findViewById(R.id.textView)
        private val imageview:ImageView = itemView.findViewById(R.id.imageView2)

        fun bind(text: String) {
            textview.text = text
            imageview.setImageResource(getImage(text))

        }
    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return users.size
    }
    fun setUsers(newUsers: MutableList<String>) {
        users.clear()
        users.addAll(newUsers)
    }



}

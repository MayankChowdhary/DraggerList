package com.example.draggable.ui.home.DraggableAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.draggable.R
import com.example.draggable.ui.home.DraggableAdapters.ListDataItems.Companion.getImage
import java.util.*


class DragDropRecyclerAdapter(private val startDragListener: OnStartDragListener) :
    RecyclerView.Adapter<DragDropRecyclerAdapter.ItemViewHolder>(),
    ItemMoveCallbackListener.Listener {
    private var users = emptyList<String>().toMutableList()

    fun setUsers(newUsers: MutableList<String>) {
        users.clear()
        val it: Iterator<String> = newUsers.iterator()
        while (it.hasNext()) {
            val value = it.next()
            users.add(value)
        }

    }

    fun saveData(){
        ListDataItems.setUsers(users)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val user = users[position]
        holder.bind(user)

        holder.imageview.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                this.startDragListener.onStartDrag(holder)
            }
            return@setOnTouchListener true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recyclerview_item, parent, false)
        return ItemViewHolder(itemView)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)
        val imageview: ImageView = itemView.findViewById(R.id.imageView)
        val imageIcon: ImageView = itemView.findViewById(R.id.imageView3)


        fun bind(text: String) {
            textView.text = text
            imageIcon.setImageResource(getImage(text))

        }


    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(users, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(users, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onRowSelected(itemViewHolder: ItemViewHolder) {

    }

    override fun onRowClear(itemViewHolder: ItemViewHolder) {

    }
}
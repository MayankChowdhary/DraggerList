package com.example.draggable.ui.home.DraggableAdapters

import android.util.Log
import com.example.draggable.R

class ListDataItems {
    companion object {
        private var users: MutableList<String> = emptyList<String>().toMutableList()

        init {
             users = mutableListOf(
                "Date",
                "Column A",
                "Phone No.",
                "Status",
                "Column B",
                "Name",
                "Patient Name",
                "Address",
                "Reminder",
                "Remarks",
                "Guardian Name",
                "Column C",
                "Column D"
            )
        }
        fun setUsers(newUsers: MutableList<String>) {
            users.clear()
            users.addAll(newUsers)
            Log.d("NewDataSet", "setUsers: $users")
        }

        fun getUsers(): MutableList<String> {
            Log.d("CurrentDataSent", "getUsers: $users")
            return users

        }
        fun getImage(text:String):Int{
            return when (text){

                "Date" -> R.drawable.calendar
                "Phone No." -> R.drawable.phone
                "Status" -> R.drawable.status
                "Name" -> R.drawable.status
                else -> R.drawable.column

            }
        }
    }


}
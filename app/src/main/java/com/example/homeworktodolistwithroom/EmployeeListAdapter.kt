package com.example.homeworktodolistwithroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmployeeListAdapter(var items:List<Note> = emptyList()): RecyclerView.Adapter<EmployeeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val listItemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return EmployeeViewHolder(listItemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.note.text = items[position].note
    }

    fun updateItems(itemsToUpdte:List<Note>) {
        items = itemsToUpdte
        notifyDataSetChanged()
    }
}

class EmployeeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val note:TextView = itemView.findViewById(R.id.add_note)
}

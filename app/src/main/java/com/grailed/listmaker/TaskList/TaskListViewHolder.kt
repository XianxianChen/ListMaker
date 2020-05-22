package com.grailed.listmaker.TaskList

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grailed.listmaker.R

class TaskListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val taskTextView = itemView.findViewById<TextView>(R.id.textView_task)
}


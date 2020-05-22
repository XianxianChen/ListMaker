package com.grailed.listmaker.TaskList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grailed.listmaker.R

class TaskListAdapter(var list: TaskList): RecyclerView.Adapter<TaskListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
     val view = LayoutInflater.from(parent.context).inflate(R.layout.task_view_holder, parent, false)
        //  ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter
    return TaskListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.taskTextView.text = list.tasks[position]
    }

}
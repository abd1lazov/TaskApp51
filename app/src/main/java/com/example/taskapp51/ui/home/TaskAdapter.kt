package com.example.taskapp51.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp51.databinding.ItemTaskBinding

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var taskList = arrayListOf<TaskModel>()

     fun addTask(taskModel: TaskModel){
        taskList.add(taskModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class ViewHolder(private var binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(taskModel: TaskModel) {
            binding.tvTitleItem.text = taskModel.title
            binding.tvDescItem.text = taskModel.desc
        }
    }

}
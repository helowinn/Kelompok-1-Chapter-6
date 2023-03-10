package com.example.myapplication.ui.dashboard.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Task
import com.example.myapplication.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.*

class DashboardViewHolder(private val binding: ItemTaskBinding) :
    RecyclerView.ViewHolder(binding.root) {


    @SuppressLint("ResourceAsColor")
    fun bind(task: Task) {
        binding.tvTitle.text = task.title
        binding.tvDesc.text = task.description
        binding.tvEvent.text = task.event
        if (task.isFinished == 1) {
            binding.tvStatus.text = "Completed"
            binding.tvStatus.setTextColor(R.color.green)
        } else {
            binding.tvStatus.text = "On Going"
            binding.tvStatus.setTextColor(R.color.purple_200)
        }
        updateDay(task.date)
        updateMonth(task.date)

    }

    private fun updateDay(time: Long) {
        val myformat = "d"
        val sdf = SimpleDateFormat(myformat)
        binding.textDateTitle.text = sdf.format(Date(time))
    }

    private fun updateMonth(time: Long) {
        val myformat = "MMM"
        val sdf = SimpleDateFormat(myformat)
        binding.textMonthTitle.text = sdf.format(Date(time))
    }

}
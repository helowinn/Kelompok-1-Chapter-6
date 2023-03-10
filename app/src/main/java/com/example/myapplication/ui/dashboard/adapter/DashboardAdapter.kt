package com.example.myapplication.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
import com.example.myapplication.data.model.Task
import com.example.myapplication.databinding.ItemTaskBinding

class DashboardAdapter (private val task : ArrayList<Task>,private val listener : OnAdapterListener):
    RecyclerView.Adapter<DashboardViewHolder>() {

    private lateinit var binding : ItemTaskBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DashboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val item = task [position]
        holder.bind(item)
        binding.relCard.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return task.size
    }

    override fun getItemId(position: Int): Long {
        return task[position].id
    }

    fun setData(list: List<Task>){
        task.clear()
        task.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(task : Task)
    }
}
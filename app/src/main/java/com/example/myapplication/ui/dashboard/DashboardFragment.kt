package com.example.myapplication.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.model.Task
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.ui.dashboard.adapter.DashboardAdapter
import com.example.myapplication.ui.detail_task.DetailTaskActivity

class DashboardFragment : Fragment(),DashboardView{

    private var _binding : FragmentDashboardBinding? = null
    private val binding : FragmentDashboardBinding
    get() = _binding!!

    private var presenter : DashboardPresenter? = null
    private lateinit var adapterDashboard: DashboardAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        presenter = DashboardPresenterImp(this)
        presenter?.getData()
    }

    private fun setupRecyclerView(){
        val recyclerViewTask = binding.taskRecycler
        adapterDashboard = DashboardAdapter(arrayListOf(),object :
            DashboardAdapter.OnAdapterListener {
            override fun onClick(task: Task) {
                startActivity(Intent(activity?.applicationContext,DetailTaskActivity::class.java)
                    .putExtra("id",task.id)
                    .putExtra("title",task.title)
                    .putExtra("desc",task.description)
                    .putExtra("event",task.event)
                    .putExtra("date",task.date)
                    .putExtra("time",task.time)
                    .putExtra("isFinished",task.isFinished)
                )
            }

        })
        recyclerViewTask.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = adapterDashboard
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onSuccess(database: List<Task>) {
        if (database.isEmpty()) {
            binding.noDataImage.visibility = View.VISIBLE
        } else {
            adapterDashboard.setData(database)
        }
    }
    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        presenter?.getData()
    }
}
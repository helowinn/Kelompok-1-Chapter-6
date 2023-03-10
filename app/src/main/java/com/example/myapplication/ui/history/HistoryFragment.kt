package com.example.myapplication.ui.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.model.Task
import com.example.myapplication.databinding.FragmentHistoryBinding
import com.example.myapplication.ui.dashboard.DashboardPresenter
import com.example.myapplication.ui.dashboard.DashboardPresenterImp
import com.example.myapplication.ui.dashboard.adapter.DashboardAdapter
import com.example.myapplication.ui.detail_task.DetailTaskActivity

class HistoryFragment : Fragment(), HistoryView {

    private var _binding : FragmentHistoryBinding?= null
    private val binding : FragmentHistoryBinding
    get() = _binding!!

    private var presenter : HistoryPresenter? = null
    private lateinit var adapterTask: DashboardAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        presenter = HistoryPresenterImp(this)
        presenter?.getDataFinished()
    }

    private fun setupRecyclerView(){
        val recyclerViewTask = binding.taskRecyclerHistory
        adapterTask = DashboardAdapter(arrayListOf(),object :
            DashboardAdapter.OnAdapterListener {
            override fun onClick(task: Task) {
                startActivity(
                    Intent(activity?.applicationContext, DetailTaskActivity::class.java)
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
            adapter = adapterTask
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        presenter?.getDataFinished()
    }

    override fun onSuccess(database: List<Task>) {
        adapterTask.setData(database)
    }
}
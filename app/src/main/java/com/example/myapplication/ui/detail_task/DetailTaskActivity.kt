package com.example.myapplication.ui.detail_task

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.example.myapplication.R
import com.example.myapplication.data.model.Task
import com.example.myapplication.databinding.ActivityDetailTaskBinding
import com.example.myapplication.databinding.DialogDeleteTaskBinding
import com.example.myapplication.ui.task.TaskActivity
import java.text.SimpleDateFormat
import java.util.*

class DetailTaskActivity : AppCompatActivity(), View.OnClickListener, DetailTaskView {

    private val binding: ActivityDetailTaskBinding by lazy {
        ActivityDetailTaskBinding.inflate(layoutInflater)
    }

    private var presenter: DetailTaskPresenter? = null
    private var id: Long = 0
    private var title: String = ""
    private var description: String = ""
    private var event: String = ""
    private var date: Long = 0
    private var time: Long = 0
    private var isFinished: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = DetailTaskPresenterImp(this)


        id = intent.getLongExtra("id", 0)
        title = intent.getStringExtra("title")!!
        description = intent.getStringExtra("desc")!!
        event = intent.getStringExtra("event")!!
        date = intent.getLongExtra("date", 0)
        time = intent.getLongExtra("time", 0)
        isFinished = intent.getIntExtra("isFinished", 0)

        binding.tvTitle.text = title
        binding.tvDesc.text = description
        binding.tvEvent.text = event
        updateDate(date)
        updateTime(date)

        initViewConfig()
    }

    private fun initViewConfig() {
        binding.imgBack.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)
        binding.btnComplete.setOnClickListener(this)
        binding.btnUpdate.setOnClickListener(this)

        if (isFinished == 1){
            binding.btnComplete.visibility = View.GONE
            binding.btnUpdate.visibility = View.GONE
        }
    }

    private fun updateDate(time: Long) {
        val myformat = "dd-MMMM-yyyy"
        val sdf = SimpleDateFormat(myformat)
        binding.tvDate.text = sdf.format(Date(time))
    }

    private fun updateTime(time: Long) {
        val myformat = "hh:mm:ss"
        val sdf = SimpleDateFormat(myformat)
        binding.tvTime.text = sdf.format(Date(time))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_delete -> {
                dialog()
            }
            R.id.btn_complete -> {
                presenter?.updateFinished(id)
            }
            R.id.btn_update -> {
                startActivity(
                    Intent(this, TaskActivity::class.java)
                        .putExtra("id", id)
                        .putExtra("title", title)
                        .putExtra("desc", description)
                        .putExtra("event", event)
                        .putExtra("date", date)
                        .putExtra("time", time)
                        .putExtra("isUpdate", true)
                )
            }
        }

    }

    private fun dialog() {
        val dialog = Dialog(this)
        val dialogResultBinding = DialogDeleteTaskBinding.inflate(LayoutInflater.from(this))
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogResultBinding.root)
        dialog.setCancelable(false)
        dialogResultBinding.imgYes.setOnClickListener {
            presenter?.deleteTask(id.toLong())
        }
        dialogResultBinding.imgBatal.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onSuccess() {
        val intent = Intent()
        intent.putExtra("isDeleted", true)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }

}
package com.example.myapplication.ui.task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCreateTaskBinding
import com.example.myapplication.ui.main.MainActivity
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class TaskActivity : AppCompatActivity(), TaskView, View.OnClickListener {

    private val binding: ActivityCreateTaskBinding by lazy {
        ActivityCreateTaskBinding.inflate(layoutInflater)
    }

    private var presenter: TaskPresenter? = null
    lateinit var myCalendar: Calendar
    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener

    var finalDate = 0L
    var finalTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = TaskPresenterImp(this)
        initViewConfig()
        initDataView()
    }

    private fun initDataView() {
        if (intent.getBooleanExtra("isUpdate", false)) {

            val date = intent.getLongExtra("date",0)
            val myformat = "EEE, d MMM yyyy"
            val sdf = SimpleDateFormat(myformat)
            val finalDate2 = sdf.format(date)

            val time = intent.getLongExtra("time",0)
            val myformatTime = "h:mm a"
            val sdfTime = SimpleDateFormat(myformatTime)
            val finalTime2 = sdfTime.format(time)

            binding.taskDate.setText(sdf.format(date))
            binding.tvTitle.text = "Update Task"
            binding.addTaskTitle.setText(intent.getStringExtra("title"))
            binding.addTaskDescription.setText(intent.getStringExtra("desc"))
            binding.taskDate.setText(finalDate2)
            binding.taskTime.setText(finalTime2)
        }
    }

    private fun initData() {
        val title = binding.addTaskTitle.text.toString()
        val desc = binding.addTaskDescription.text.toString()
        val event = binding.taskEvent.text.toString()

        if (intent.getBooleanExtra("isUpdate", false)) {
            presenter?.updateTask(intent.getLongExtra("id", 0), title, desc, event, finalDate, finalTime)
        } else
            presenter?.addTask(0, title, desc, event, finalDate, finalTime)
    }

    private fun initViewConfig() {
        binding.taskDate.setOnClickListener(this)
        binding.taskTime.setOnClickListener(this)
        binding.addTask.setOnClickListener(this)

    }

    private fun setListener() {
        myCalendar = Calendar.getInstance()

        dateSetListener =
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDate()

            }

        val datePickerDialog = DatePickerDialog(
            this, dateSetListener, myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun updateDate() {
        val myformat = "EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(myformat)
        finalDate = myCalendar.time.time
        binding.taskDate.setText(sdf.format(myCalendar.time))
    }

    private fun setTimeListener() {
        myCalendar = Calendar.getInstance()

        timeSetListener =
            TimePickerDialog.OnTimeSetListener() { _: TimePicker, hourOfDay: Int, min: Int ->
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                myCalendar.set(Calendar.MINUTE, min)
                updateTime()
            }

        val timePickerDialog = TimePickerDialog(
            this, timeSetListener, myCalendar.get(Calendar.HOUR_OF_DAY),
            myCalendar.get(Calendar.MINUTE), false
        )
        timePickerDialog.show()
    }

    private fun updateTime() {
        val myformat = "h:mm a"
        val sdf = SimpleDateFormat(myformat)
        finalTime = myCalendar.time.time
        binding.taskTime.setText(sdf.format(myCalendar.time))

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.taskDate -> {
                setListener()
            }
            R.id.taskTime -> {
                setTimeListener()
            }
            R.id.addTask -> {
                initData()
            }
            R.id.img_back -> {
                onBackPressed()
            }
        }
    }

    override fun onSuccess() {
        Toast.makeText(this, "berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSuccessUpdate() {
        val newIntent = Intent(this, MainActivity::class.java)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(newIntent)
        finish()
    }

    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }


}
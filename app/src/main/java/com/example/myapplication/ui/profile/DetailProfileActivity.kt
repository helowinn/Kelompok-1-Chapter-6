package com.example.myapplication.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.data.local.SharedPref
import com.example.myapplication.data.model.Task
import com.example.myapplication.data.model.User
import com.example.myapplication.databinding.ActivityDetailProfileBinding
import com.example.myapplication.ui.login.LoginActivity

@Suppress("DEPRECATION")
class DetailProfileActivity : AppCompatActivity(),ProfileView {

    private val binding : ActivityDetailProfileBinding by lazy{
        ActivityDetailProfileBinding.inflate(layoutInflater)
    }

    private var presenter : ProfilePresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val user = intent.getStringExtra("username")

        presenter = ProfilePresenterImp(this)
        presenter?.getDataUser(user.toString())

        binding.btnSave.setOnClickListener {
            val username = binding.inpUsername
            val password = binding.inpPassword
            val email = binding.inpEmail
            (presenter as ProfilePresenterImp)?.updateUser(SharedPref.id!!,username.text.toString(),password.text.toString(),email.text.toString())
        }

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onSuccessCompleted(database: List<Task>) {
        TODO("Not yet implemented")
    }

    override fun onSuccessTask(database: List<Task>) {
        TODO("Not yet implemented")
    }

    override fun onSuccess() {
        Toast.makeText(this, "User berhasil Dirubah", Toast.LENGTH_SHORT).show()
        presenter?.logout()
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        this.finish()
    }

    override fun onSuccessUser(user: User) {
        binding.inpUsername.setText(user.username)
        binding.inpEmail.setText(user.email)
        binding.inpPassword.setText(user.password)
    }
}
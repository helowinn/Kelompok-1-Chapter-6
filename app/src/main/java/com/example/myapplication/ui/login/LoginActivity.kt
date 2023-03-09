package com.example.myapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity(), LoginView {
    private var presenter : LoginPresenter? = null
    private val binding : ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = LoginPresenterImp(this)

        val username = binding.inpUsername
        val password = binding.inpPassword

        binding.btnLogin.setOnClickListener {
            if (username.text.toString().isEmpty()){
                username.error = "Username harus di isi"
            }else if (password.text.toString().isEmpty()){
                password.error = "Password harus diisi"
            }else{
                presenter?.login(username.text.toString(),password.text.toString())
            }
        }

        binding.tvCreateNow.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

    }

    override fun onSuccess() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        finish()
    }
}
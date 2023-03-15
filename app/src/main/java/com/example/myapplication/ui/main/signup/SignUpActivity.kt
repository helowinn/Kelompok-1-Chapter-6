package com.example.myapplication.ui.main.signup

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.example.myapplication.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity(), SignUpView {

    private val binding : ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    private var presenter : SignUpPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = SignUpPresenterImp(this)

        var checker = true
        val username = binding.inpUsername
        val password = binding.inpPassword
        val repassword = binding.inpRepassword
        val email = binding.inpEmail

        binding.btnSign.setOnClickListener {
            if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                email.error = "Mohon isi email yang benar"
            }
            else {
                if(repassword.text.toString() != password.text.toString()){
                    repassword.error = "Password tidak sama"
                }
                else if (checker){
                    (presenter as SignUpPresenterImp).signUp(username.text.toString(), password.text.toString(), email.text.toString())
                }
            }
            checker = true
        }

    }

    override fun onSuccess() {
        Toast.makeText(this, "User berhasil didaftarkan", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
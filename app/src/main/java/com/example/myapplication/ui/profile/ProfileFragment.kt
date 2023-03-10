package com.example.myapplication.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.data.local.SharedPref
import com.example.myapplication.data.model.Task
import com.example.myapplication.data.model.User
import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.ui.login.LoginActivity

class ProfileFragment : Fragment(),ProfileView {

    private var _binding : FragmentProfileBinding? = null
    private val binding : FragmentProfileBinding
    get() = _binding!!

    private var presenter : ProfilePresenter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ProfilePresenterImp(this)
        presenter?.getData()
        presenter?.getDataFinished()
        presenter?.getDataUser(SharedPref.username.toString())

        binding.linProfile.setOnClickListener {
            val intent = Intent(activity, DetailProfileActivity::class.java)
                .putExtra("username",SharedPref.username)
            startActivity(intent)
        }

        binding.linLogout.setOnClickListener {
            presenter?.logout()
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            activity?.finish()
        }

    }

    override fun onSuccessCompleted(database: List<Task>) {
        binding.tvComplete.text = ""+database.size
    }

    override fun onSuccessTask(database: List<Task>) {
        binding.tvTask.text = ""+database.size
    }

    override fun onSuccess() {
        TODO("Not yet implemented")
    }

    override fun onSuccessUser(user: User) {
        binding.tvUsername.text = user.username
        binding.tvEmail.text = user.email

    }



}
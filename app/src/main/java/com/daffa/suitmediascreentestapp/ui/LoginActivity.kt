package com.daffa.suitmediascreentestapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daffa.suitmediascreentestapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnApply.setOnClickListener {
            sendName()
        }
    }

    private fun sendName() {
        val name = binding.etInputName.text.toString()

        Intent(this, UserScreenActivity::class.java).also {
            it.putExtra(UserScreenActivity.NAME, name)
            startActivity(it)
        }
    }
}
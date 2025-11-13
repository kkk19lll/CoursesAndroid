package com.kkk19lll.coursesandroid.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.kkk19lll.coursesandroid.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.isEnabled = false

        val emailFilter = InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                val c = source[i]
                if (c in '\u0400'..'\u04FF') {
                    return@InputFilter ""
                }
            }
            null
        }
        binding.etEmail.filters = arrayOf(emailFilter)

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkFields()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.etEmail.addTextChangedListener(watcher)
        binding.etPassword.addTextChangedListener(watcher)

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnVK.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/")))
        }

        binding.btnOK.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://ok.ru/")))
        }

        binding.tvRegister.isEnabled = false
        binding.tvForgotPassword.isEnabled = false
    }

    private fun checkFields() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val emailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        binding.btnLogin.isEnabled = emailValid && password.isNotEmpty()
    }
}

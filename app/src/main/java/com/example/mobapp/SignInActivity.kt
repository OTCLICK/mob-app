package com.example.mobapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class SignInActivity : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignupLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        tvSignupLink = findViewById(R.id.tv_signup_link)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            var isValid = true

            // Очистка предыдущих ошибок
            etEmail.error = null
            etPassword.error = null

            if (email.isEmpty()) {
                etEmail.error = "Email не может быть пустым"
                isValid = false
            }

            if (password.isEmpty()) {
                etPassword.error = "Пароль не может быть пустым"
                isValid = false
            }

            if (isValid) {
                Toast.makeText(this, "Вход выполнен!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

        tvSignupLink.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
package com.example.mobapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : BaseActivity() {

    private lateinit var etUsername: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText
    private lateinit var btnRegister: Button
    private lateinit var tvSigninLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etUsername = findViewById(R.id.et_username)
        etEmail = findViewById(R.id.et_signup_email)
        etPassword = findViewById(R.id.et_signup_password)
        etConfirmPassword = findViewById(R.id.et_confirm_password)
        btnRegister = findViewById(R.id.btn_register)
        tvSigninLink = findViewById(R.id.tv_signin_link)

        btnRegister.setOnClickListener {
            if (validateForm()) {
                val username = etUsername.text.toString().trim()
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()

                val resultIntent = Intent().apply {
                    putExtra("username", username)
                    putExtra("email", email)
                    putExtra("user", User(username, email, password))
                }

                Log.d("DEBUG", "Calling finish() in SignUpActivity — returning to SignInActivity")
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        tvSigninLink.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        val username = etUsername.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        etUsername.error = null
        etEmail.error = null
        etPassword.error = null
        etConfirmPassword.error = null

        if (username.isEmpty()) {
            etUsername.error = "Имя обязательно"
            isValid = false
        }

        if (email.isEmpty()) {
            etEmail.error = "Почта обязательна"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Неверный формат почты"
            isValid = false
        }

        if (password.isEmpty()) {
            etPassword.error = "Пароль обязателен"
            isValid = false
        } else if (password.length < 3) {
            etPassword.error = "Минимум 3 символа"
            isValid = false
        } else if (!password.any { it.isUpperCase() }) {
            etPassword.error = "Должна быть заглавная буква"
            isValid = false
        } else if (!password.any { it.isDigit() }) {
            etPassword.error = "Должна быть цифра"
            isValid = false
        }

        if (confirmPassword.isEmpty()) {
            etConfirmPassword.error = "Подтвердите пароль"
            isValid = false
        } else if (confirmPassword != password) {
            etConfirmPassword.error = "Пароли не совпадают"
            isValid = false
        }

        return isValid
    }
}
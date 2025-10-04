package com.example.mobapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.fragment.app.Fragment
import com.example.mobapp.MainActivity
import com.example.mobapp.User
import com.example.mobapp.databinding.ActivitySignUpBinding

class SignUpFragment : Fragment() {

    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivitySignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            if (validateForm()) {
                val user = User(
                    username = binding.etUsername.text.toString().trim(),
                    email = binding.etSignupEmail.text.toString().trim(),
                    password = binding.etSignupPassword.text.toString().trim()
                )

                (activity as? MainActivity)?.onUserRegistered(user)
            }
        }

        binding.tvSigninLink.setOnClickListener {
            (activity as? MainActivity)?.navigateToSignIn()
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true
        val username = binding.etUsername.text.toString().trim()
        val email = binding.etSignupEmail.text.toString().trim()
        val password = binding.etSignupPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()

        binding.etUsername.error = null
        binding.etSignupEmail.error = null
        binding.etSignupPassword.error = null
        binding.etConfirmPassword.error = null

        if (username.isEmpty()) {
            binding.etUsername.error = "Имя обязательно"
            isValid = false
        }

        if (email.isEmpty()) {
            binding.etSignupEmail.error = "Почта обязательна"
            isValid = false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etSignupEmail.error = "Неверный формат почты"
            isValid = false
        }

        if (password.isEmpty()) {
            binding.etSignupPassword.error = "Пароль обязателен"
            isValid = false
        } else if (password.length < 3) {
            binding.etSignupPassword.error = "Минимум 3 символа"
            isValid = false
        } else if (!password.any { it.isUpperCase() }) {
            binding.etSignupPassword.error = "Должна быть заглавная буква"
            isValid = false
        } else if (!password.any { it.isDigit() }) {
            binding.etSignupPassword.error = "Должна быть цифра"
            isValid = false
        }

        if (confirmPassword.isEmpty()) {
            binding.etConfirmPassword.error = "Подтвердите пароль"
            isValid = false
        } else if (confirmPassword != password) {
            binding.etConfirmPassword.error = "Пароли не совпадают"
            isValid = false
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
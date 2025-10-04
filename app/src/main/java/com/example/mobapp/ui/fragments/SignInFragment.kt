package com.example.mobapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobapp.MainActivity
import com.example.mobapp.databinding.ActivitySignInBinding
import com.google.android.material.textfield.TextInputEditText
import com.example.mobapp.User // ← Убедись, что User лежит в com.example.mobapp!

class SignInFragment : Fragment() {

    private var _binding: ActivitySignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivitySignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = arguments?.getSerializable("user") as? User
        if (user != null) {
            binding.etEmail.setText(user.email)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            var isValid = true
            binding.etEmail.error = null
            binding.etPassword.error = null

            if (email.isEmpty()) {
                binding.etEmail.error = "Email не может быть пустым"
                isValid = false
            }
            if (password.isEmpty()) {
                binding.etPassword.error = "Пароль не может быть пустым"
                isValid = false
            }

            if (isValid) {
                Toast.makeText(requireContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show()
                (activity as? MainActivity)?.navigateToHome()
            }
        }

        binding.tvSignupLink.setOnClickListener {
            (activity as? MainActivity)?.navigateToSignUp()
        }
    }

    fun setUserEmail(email: String) {
        binding.etEmail.setText(email)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
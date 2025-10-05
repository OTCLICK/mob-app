package com.example.mobapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobapp.MainActivity
import com.example.mobapp.R
import com.google.android.material.textfield.TextInputEditText
import com.example.mobapp.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<SignInFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.user?.let { user ->
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
                findNavController().navigate(R.id.action_signin_to_home)
            }
        }

        binding.tvSignupLink.setOnClickListener {
            findNavController().navigate(R.id.action_signin_to_signup)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
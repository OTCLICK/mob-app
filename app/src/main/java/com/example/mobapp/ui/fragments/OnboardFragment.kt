package com.example.mobapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobapp.MainActivity
import com.example.mobapp.R
import com.example.mobapp.databinding.FragmentOnboardBinding

//class OnboardFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View = inflater.inflate(R.layout.activity_onboard, container, false)
//
//        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        view.findViewById<Button>(R.id.btn_next_to_signin).setOnClickListener {
//            (activity as? MainActivity)?.navigateToSignIn()
//        }
//    }
//}

class OnboardFragment : Fragment() {

    private var _binding: FragmentOnboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNextToSignin.setOnClickListener {
            findNavController().navigate(R.id.action_onboard_to_signin)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
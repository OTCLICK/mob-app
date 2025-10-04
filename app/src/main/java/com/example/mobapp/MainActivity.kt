package com.example.mobapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.mobapp.ui.fragments.HomeFragment
import com.example.mobapp.ui.fragments.OnboardFragment
import com.example.mobapp.ui.fragments.SignInFragment
import com.example.mobapp.ui.fragments.SignUpFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<OnboardFragment>(R.id.fragment_container)
                setReorderingAllowed(true)
                addToBackStack("onboard")
            }
        }
    }

    fun navigateToSignIn() {
        supportFragmentManager.commit {
            replace<SignInFragment>(R.id.fragment_container)
            setReorderingAllowed(true)
            addToBackStack("signin")
        }
    }

    fun navigateToSignUp() {
        supportFragmentManager.commit {
            replace<SignUpFragment>(R.id.fragment_container)
            setReorderingAllowed(true)
            addToBackStack("signup")
        }
    }

    fun navigateToHome() {
        // Удаляем всё после "onboard" (включая signin/signup), чтобы из Home возвращаться сразу на Onboard
        supportFragmentManager.popBackStack("onboard", 0) // 0 = POP_BACK_STACK_INCLUSIVE = false
        supportFragmentManager.commit {
            replace<HomeFragment>(R.id.fragment_container)
            setReorderingAllowed(true)
            addToBackStack("home")
        }
    }

    fun onUserRegistered(user: User) {
        val signInFragment = supportFragmentManager.findFragmentByTag("signin") as? SignInFragment
        if (signInFragment != null) {
            signInFragment.setUserEmail(user.email)
        } else {
            val fragment = SignInFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("user", user)
                }
            }
            supportFragmentManager.commit {
                replace(R.id.fragment_container, fragment, "signin")
                setReorderingAllowed(true)
                addToBackStack("signin")
            }
        }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 1) {
            // Остался только Onboard — выходим из приложения
            super.onBackPressed()
        } else {
            // Возвращаемся на предыдущий фрагмент
            supportFragmentManager.popBackStack()
        }
    }
}
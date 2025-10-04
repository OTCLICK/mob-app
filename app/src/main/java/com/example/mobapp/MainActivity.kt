package com.example.mobapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
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
        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
package com.example.mobapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)
    }
}

//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        if (savedInstanceState == null) {
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                replace<OnboardFragment>(R.id.fragment_container)
//            }
//        }
//    }
//
//    fun navigateToSignIn() {
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            replace<SignInFragment>(R.id.fragment_container)
//            addToBackStack("to_signin")
//        }
//    }
//
//    fun navigateToSignUp() {
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            replace<SignUpFragment>(R.id.fragment_container)
//            addToBackStack("to_signup")
//        }
//    }
//
//    fun navigateToHome() {
//        while (supportFragmentManager.backStackEntryCount > 0) {
//            supportFragmentManager.popBackStackImmediate()
//        }
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            replace<HomeFragment>(R.id.fragment_container)
//            addToBackStack("to_home")
//        }
//    }
//
//    fun onUserRegistered(user: User) {
//        val signInFragment = supportFragmentManager.findFragmentByTag("to_signin") as? SignInFragment
//        if (signInFragment != null) {
//            signInFragment.setUserEmail(user.email)
//        } else {
//            val fragment = SignInFragment().apply {
//                arguments = Bundle().apply {
//                    putSerializable("user", user)
//                }
//            }
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                replace(R.id.fragment_container, fragment)
//                addToBackStack("to_signin_from_register")
//            }
//        }
//    }
//
//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount == 0) {
//            super.onBackPressed()
//        } else {
//            supportFragmentManager.popBackStack()
//        }
//    }
//}
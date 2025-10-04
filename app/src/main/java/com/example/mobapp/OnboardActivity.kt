//package com.example.mobapp
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import androidx.appcompat.app.AppCompatActivity
//
//class OnboardActivity : BaseActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_onboard)
//
//        val btnNext = findViewById<Button>(R.id.btn_next_to_signin)
//        btnNext.setOnClickListener {
//            startActivity(Intent(this, SignInActivity::class.java))
//        }
//    }
//}
//package com.example.mobapp
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.TextView
//import android.widget.Toast
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.appcompat.app.AppCompatActivity
//import com.google.android.material.textfield.TextInputEditText
//
//class SignInActivity : BaseActivity() {
//
//    private lateinit var etEmail: TextInputEditText
//    private lateinit var etPassword: TextInputEditText
//    private lateinit var btnLogin: Button
//    private lateinit var tvSignupLink: TextView
//
//    private val signUpResultLauncher = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        Log.d("DEBUG", "signUpResultLauncher: received result from SignUpActivity")
//
//        if (result.resultCode == RESULT_OK) {
//            val data = result.data
//
//            val user = data?.getSerializableExtra("user") as? User
//            if (user != null) {
//                Log.d("DEBUG", "User received: ${user.email}")
//                Toast.makeText(
//                    this,
//                    "Получен User: ${user.username} (${user.email})",
//                    Toast.LENGTH_LONG
//                ).show()
//                etEmail.setText(user.email)
//            }
////            val username = data?.getStringExtra("username") ?: ""
////            val email = data?.getStringExtra("email") ?: ""
////            if (username.isNotEmpty() && email.isNotEmpty()) {
////                Toast.makeText(
////                    this,
////                    "Получены данные как строки: $username ($email)",
////                    Toast.LENGTH_LONG
////                ).show()
////                etEmail.setText(email)
////            }
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_in)
//
//        etEmail = findViewById(R.id.et_email)
//        etPassword = findViewById(R.id.et_password)
//        btnLogin = findViewById(R.id.btn_login)
//        tvSignupLink = findViewById(R.id.tv_signup_link)
//
//        btnLogin.setOnClickListener {
//            val email = etEmail.text.toString().trim()
//            val password = etPassword.text.toString().trim()
//
//            var isValid = true
//
//            etEmail.error = null
//            etPassword.error = null
//
//            if (email.isEmpty()) {
//                etEmail.error = "Email не может быть пустым"
//                isValid = false
//            }
//
//            if (password.isEmpty()) {
//                etPassword.error = "Пароль не может быть пустым"
//                isValid = false
//            }
//
//            if (isValid) {
//                Toast.makeText(this, "Вход выполнен!", Toast.LENGTH_SHORT).show()
//
//                startActivity(Intent(this, HomeActivity::class.java))
//            }
//        }
//
//        tvSignupLink.setOnClickListener {
//            val intent = Intent(this, SignUpActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
//            signUpResultLauncher.launch(intent)
//        }
//    }
//}
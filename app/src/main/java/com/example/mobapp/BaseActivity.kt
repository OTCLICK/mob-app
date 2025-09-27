package com.example.mobapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LIFECYCLE", "${javaClass.simpleName} > СОЗДАНИЕ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "${javaClass.simpleName} > СТАРТ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "${javaClass.simpleName} > ВОЗОБНОВЛЕНИЕ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "${javaClass.simpleName} > ПАУЗА")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "${javaClass.simpleName} > ОСТАНОВКА")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LIFECYCLE", "${javaClass.simpleName} > ПЕРЕЗАПУСК")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "${javaClass.simpleName} > УНИЧТОЖЕНИЕ")
    }
}
package com.example.random_dog.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.random_dog.R
import com.example.random_dog.dog.DogActivity

private const val SPLASH_DELAY = 2000L

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this, DogActivity::class.java))
            finish()
        }, SPLASH_DELAY)
    }
}
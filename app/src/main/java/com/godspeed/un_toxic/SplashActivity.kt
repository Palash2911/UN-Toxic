package com.godspeed.un_toxic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class SplashActivity : AppCompatActivity() {
    lateinit var label:TextView ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        supportActionBar?.hide();

        setContentView(R.layout.activity_splash)
        label = findViewById(R.id.label)


    Handler().postDelayed({
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    },2000)

    }
}
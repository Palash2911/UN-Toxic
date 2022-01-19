package com.godspeed.un_toxic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btn = findViewById<Button>(R.id.submitbtn)

        btn.setOnClickListener {
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
            finish()
        }
    }
}
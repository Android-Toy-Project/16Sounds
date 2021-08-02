package com.example.a16sounds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goProfileActivity(v : View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
}
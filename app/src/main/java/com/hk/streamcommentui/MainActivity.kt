package com.hk.streamcommentui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val btn  = findViewById<TextView>(R.id.textView)
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener{
            startActivity(Intent(this,MainFlickScreen::class.java))
        }
    }
}
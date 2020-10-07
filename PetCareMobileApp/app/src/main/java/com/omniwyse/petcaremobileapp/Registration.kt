package com.omniwyse.petcaremobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        textView2.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
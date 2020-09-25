package com.omniwyse.petcare

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pet = findViewById<TextView>(R.id.petcare)// need to use data binding
        val data = "PetCare"
        val content = SpannableString(data)
        content.setSpan(UnderlineSpan(), 0, data.length, 0)
        pet.text = content

        println("before onclick")
        pet.setOnClickListener{
            println("in onclick")
            startActivity(Intent(this, login::class.java))
            }
        }

    }


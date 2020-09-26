package com.omniwyse.petcaremobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val skip=findViewById<TextView>(R.id.skip)
        val data = "Skip Now"
        val content = SpannableString(data)
        content.setSpan(UnderlineSpan(), 0, data.length, 0)
        skip.text = content
    }
}
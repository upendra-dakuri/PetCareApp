package com.omniwyse.petcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.EditText
import android.widget.TextView

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<EditText>(R.id.username)
        findViewById<EditText>(R.id.password)
        val skip=findViewById<TextView>(R.id.skip)
        val udata = "Skip Now"
        val content = SpannableString(udata)
        content.setSpan(UnderlineSpan(), 0, udata.length, 0)
        skip.text = content
    }
}
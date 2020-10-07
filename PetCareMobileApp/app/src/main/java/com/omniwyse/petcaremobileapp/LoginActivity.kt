package com.omniwyse.petcaremobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.databinding.DataBindingUtil
import com.omniwyse.petcaremobileapp.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //data binding used
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        //SpannableString for Underline the text
        val data = "Skip Now"
        val content = SpannableString(data)
        content.setSpan(UnderlineSpan(), 0, data.length, 0)
        skip.text = content

        textView2.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
       button.setOnClickListener {
            startActivity(Intent(this,HomeScreenActivity::class.java))
        }
    }
}
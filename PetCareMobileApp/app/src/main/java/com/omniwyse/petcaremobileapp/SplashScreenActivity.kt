package com.omniwyse.petcaremobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val title=findViewById<TextView>(R.id.petcare)  //need to use data binding
            Handler().postDelayed(
            {
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }, 3000)
        val name = "PetCare"
        val content = SpannableString(name)
        content.setSpan(UnderlineSpan(), 0, name.length, 0)
        title.text = content
    }
}
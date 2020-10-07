package com.omniwyse.petcaremobileapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.omniwyse.petcaremobileapp.databinding.ActivitySplashScreenBinding
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
           /* val type = Typeface.createFromAsset(assets, "font/cambay_bold.ttf")
            petcare.setTypeface(type)*/
            /* setContentView(R.layout.activity_splash_screen)
        val title=findViewById<TextView>(R.id.petcare)*/

        //used data binding
            binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        //Splash screen Code
            Handler().postDelayed(
                {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }, 3000
            )

        //SpannableString for Underline the text
        val name = "PetCare"
        val content = SpannableString(name)
        content.setSpan(UnderlineSpan(), 0, name.length, 0)
        binding.petcare.text = content
    }
}
package com.omniwyse.petcaremobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.omniwyse.petcaremobileapp.databinding.ActivityLoginBinding
import com.omniwyse.petcaremobileapp.model.LoginData
import com.omniwyse.petcaremobileapp.model.LoginResponse
import com.omniwyse.petcaremobileapp.services.FactoryAPI
import com.omniwyse.petcaremobileapp.services.PetcareServices
import com.omniwyse.petcaremobileapp.services.RegisterResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        mAuth = FirebaseAuth.getInstance()
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

           loginUsers(textView3.text.toString(),editTextTextPassword.text.toString())
        }

    }

   /* private fun loginUsers(mobile: String, password: String) {
        var error = 0
        if (mobile.length < 10) {
            error = error + 1
            textView3.setError("Enter Username")

        }
        if (password.equals("")) {
            editTextTextPassword.setError("Enter Password")
            error = ++error;
        }

        if (error == 0) {
            loginProcess(mobile, password);
        }
    }*/
        private fun loginUsers(mobile: String, password: String)
        {
            val loginInfo = LoginData(mobile,password)
            println(" Login information "+loginInfo);
            val petcare = FactoryAPI.buildService(PetcareServices::class.java)
            val requestCall: Call<LoginResponse> = petcare.login(loginInfo)
            requestCall.enqueue(object: Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    println("in success  body is :")
                    println("response body is :"+response.body())
                    if (response.isSuccessful) {
                        val msg = response.body().toString()
                        Toast.makeText(this@LoginActivity, msg, Toast.LENGTH_LONG).show()
                        println("success response " + response.body())
                        startActivity(Intent(this@LoginActivity,HomeScreenActivity::class.java))
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    println("error in Login is  : "+t)
                    Toast.makeText(applicationContext,"In failure ",Toast.LENGTH_SHORT).show()
                }

            })

        }

}
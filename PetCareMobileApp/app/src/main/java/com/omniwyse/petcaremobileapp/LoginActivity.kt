package com.omniwyse.petcaremobileapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.omniwyse.petcaremobileapp.databinding.ActivityLoginBinding
import com.omniwyse.petcaremobileapp.model.LoginData
import com.omniwyse.petcaremobileapp.model.LoginResponse
import com.omniwyse.petcaremobileapp.services.FactoryAPI
import com.omniwyse.petcaremobileapp.services.PetcareServices
import com.omniwyse.petcaremobileapp.services.RegisterResponse
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationNum=""
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        mAuth = FirebaseAuth.getInstance()
        mAuth.setLanguageCode(Locale.getDefault().language)
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
        checkBox.setOnClickListener {
            verifyOTP()
        }
        fpwd.setOnClickListener{
            startActivity(Intent(this,ForgotPassword::class.java))
        }
        skip.setOnClickListener{
            startActivity(Intent(this,HomeScreenActivity::class.java))
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
    private fun verificationCallbacks() {
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(Credentials: PhoneAuthCredential) {
               /* Toast.makeText(this@LoginActivity, "verification done$Credentials", Toast.LENGTH_LONG).show()
                val intent = Intent(this@LoginActivity, LoginOTPActivity::class.java)
                startActivity(intent)*/
            }
            override fun onVerificationFailed(e: FirebaseException) {
                println("exception is :" + e)
                Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()

            }
            override fun onCodeSent(verfication: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verfication, token)
                verificationNum=verfication.toString()
                println("verificationNum is :"+verificationNum)
                Toast.makeText(this@LoginActivity, "Verification code sent to mobile", Toast.LENGTH_LONG).show()
                resendToken = token
                val intent = Intent(this@LoginActivity, LoginOTPActivity::class.java)
                intent.putExtra("otp",verificationNum)
                startActivity(intent)
            }
        }
    }
    private fun verifyOTP() {
        verificationCallbacks()
        val phnNo = textView3.text.toString()
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber("+91"+phnNo) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(mCallbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
      /*  PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91" + phnNo,
            60,
            java.util.concurrent.TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            mCallbacks
        )*/
    }
}
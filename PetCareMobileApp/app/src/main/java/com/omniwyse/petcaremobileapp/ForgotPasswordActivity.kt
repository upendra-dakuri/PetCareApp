package com.omniwyse.petcaremobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.omniwyse.petcaremobileapp.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_forgot_password.button
import java.util.*
import java.util.concurrent.TimeUnit

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationNumber = ""
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        mAuth = FirebaseAuth.getInstance()
        mAuth.setLanguageCode(Locale.getDefault().language)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        button.setOnClickListener {
            verifyOTPCode()
        }
    }

    private fun verificationCallbacks() {
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(Credentials: PhoneAuthCredential) {}
            override fun onVerificationFailed(e: FirebaseException) {
                println("exception is :" + e)
                Toast.makeText(this@ForgotPasswordActivity, e.message, Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(
                verfication: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verfication, token)
                verificationNumber = verfication
                println("verificationNum is :" + verificationNumber)
                Toast.makeText(
                    this@ForgotPasswordActivity,
                    "Verification code sent to mobile",
                    Toast.LENGTH_LONG
                ).show()
                resendToken = token
                val intent = Intent(this@ForgotPasswordActivity, ForgotPasswordOTPActivity::class.java)
                intent.putExtra("otp", verificationNumber)
                startActivity(intent)
            }
        }
    }

    private fun verifyOTPCode() {
        verificationCallbacks()
        // val phnNo = intent.getStringExtra("mobile")
        val phnNo = number.text.toString()
        println("phone number is: " + phnNo)
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber("+91" + phnNo)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(mCallbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        /* PhoneAuthProvider.getInstance().verifyPhoneNumber(
             "+91" + phnNo,
             60,
             java.util.concurrent.TimeUnit.SECONDS,
             TaskExecutors.MAIN_THREAD,
             mCallbacks
         )*/
    }
}
package com.omniwyse.petcaremobileapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_o_t_p_.*
import java.util.*

class ForgotPasswordOTPActivity : AppCompatActivity() {
    var verificationNumber = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    lateinit var mAuth: FirebaseAuth
    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        mAuth = FirebaseAuth.getInstance()
        mAuth.setLanguageCode(Locale.getDefault().language)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_o_t_p)
        submit.setOnClickListener { view: View? ->
            authenticateOTPCode()
        }
    }

    private fun authenticateOTPCode() {
        verificationNumber = intent.getStringExtra("otp")
        val verifiNo = verificationCode.text.toString()
        if (verifiNo.isEmpty() || verifiNo.length < 6) {
            verificationCode.setError("Enter Valid Code...")
            verificationCode.requestFocus()
        } else {
            val credential: PhoneAuthCredential =
                PhoneAuthProvider.getCredential(verificationNumber, verifiNo)
            println("verification id in sign in is :" + verificationNumber)
            println("verifiNo in sign in is :" + verifiNo)
            signIn(credential)
        }
    }

    private fun signIn(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task: Task<AuthResult> ->
                println("credentials is :" + credential)
                if (task.isSuccessful) {
                    toast("Authentication Successful")
                    val intent = Intent(this@ForgotPasswordOTPActivity, ResetPasswordActivity::class.java)
                    startActivity(intent)
                } else {
                    toast("Authentication Failed please check...")
                }
            }
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
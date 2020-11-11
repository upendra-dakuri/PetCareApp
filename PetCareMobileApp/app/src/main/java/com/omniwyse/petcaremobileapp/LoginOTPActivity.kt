package com.omniwyse.petcaremobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_o_t_p_.*
import java.util.*

class LoginOTPActivity : AppCompatActivity() {
    var verificationId=""
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_o_t_p)
        mAuth = FirebaseAuth.getInstance()
        mAuth.setLanguageCode(Locale.getDefault().language)
        submit.setOnClickListener { view: View? ->
            authenticateOTP()
           /* val intent = Intent(this@LoginOTPActivity, HomeScreenActivity::class.java)
            startActivity(intent)*/
        }
    }
    private fun authenticateOTP () {

        val verifiNo = verificationCode.text.toString()
        println("verifiNo is : " + verifiNo)
        verificationId=intent.getStringExtra("otp")
        println("verification id in authenticate  is :"+verificationId)
        if(verifiNo.isEmpty() || verifiNo.length<6)
        {
            verificationCode.setError("Enter Valid Code...")
            verificationCode.requestFocus()
        }
        else {
            val credential: PhoneAuthCredential =
                PhoneAuthProvider.getCredential(verificationId, verifiNo)
            println("verification id in sign in is :"+verificationId)
            println("verifiNo in sign in is :"+verifiNo)
            signIn(credential)
        }
    }
    private fun signIn(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task: Task<AuthResult> ->
                println("credentials is :" +credential)
                if (task.isSuccessful) {
                    toast("Authentication Successful")
                    val intent = Intent(this@LoginOTPActivity, HomeScreenActivity::class.java)
                    startActivity(intent)
                }
                else
                {
                    toast("Authentication Failed please check...")
                }
            }
    }
    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}
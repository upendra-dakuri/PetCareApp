package com.omniwyse.petcaremobileapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_o_t_p_.*

class OTP_Activity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    var verificationId=""
    //private val KEY_VERIFICATION_ID = "key_verification_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o_t_p_)
        mAuth = FirebaseAuth.getInstance()
      /*  button.setOnClickListener {
            startActivity(Intent(this, Registration::class.java))
        }*/


        submit.setOnClickListener { view: View? ->
            authenticate()
            val uname=intent.getStringExtra("uname")
            val email=intent.getStringExtra("mail")
            val mobile=  intent.getStringExtra("number")
            val password=  intent.getStringExtra("pwd")
            val cpassword=intent.getStringExtra("cpwd")
            val address=intent.getStringExtra("addre")
            println("uname is :"+uname)
            val intent = Intent(this@OTP_Activity, RegistrationActivity::class.java)
            intent.putExtra("name", uname)
            intent.putExtra("mail", email)
            intent.putExtra("number", mobile)
            intent.putExtra("pwd", password)
            intent.putExtra("cpwd", cpassword)
            intent.putExtra("addre", address)
            startActivity(intent)
        }
        /*if (verificationId == null && savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        }*/
    }
    private fun authenticate () {

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
                    val user = task.result.user
                    startActivity(Intent(this, RegistrationActivity::class.java))
                    val uname=intent.getStringExtra("uname")
                    val email=intent.getStringExtra("mail")
                    val mobile=  intent.getStringExtra("number")
                    val password=  intent.getStringExtra("pwd")
                    val cpassword=intent.getStringExtra("cpwd")
                    val address=intent.getStringExtra("addre")
                    println("uname is :"+uname)
                    val intent = Intent(this@OTP_Activity, RegistrationActivity::class.java)
                    intent.putExtra("name", uname)
                    intent.putExtra("mail", email)
                    intent.putExtra("number", mobile)
                    intent.putExtra("pwd", password)
                    intent.putExtra("cpwd", cpassword)
                    intent.putExtra("addre", address)
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
   /* override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_VERIFICATION_ID, verificationId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        verificationId = savedInstanceState.getString(KEY_VERIFICATION_ID)!!
    }*/
}
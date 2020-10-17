package com.omniwyse.petcaremobileapp
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {
    var verificationId = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    lateinit var mAuth: FirebaseAuth
    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mAuth = FirebaseAuth.getInstance()
        mAuth.setLanguageCode(Locale.getDefault().language)

        exist.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        getotp.setOnClickListener { view: View? ->
                      //verify()
            val intent = Intent(this@RegisterActivity, OTP_Activity::class.java)
            val username: String = name.text.toString()
            val email:String=email.text.toString()
            val mobile: String = phnNo.text.toString()
            val password: String = password.text.toString()
            val cpassword: String = cpassword.text.toString()
            val address: String = address.text.toString()
            intent.putExtra("uname", username)
            intent.putExtra("mail", email)
            intent.putExtra("number", mobile)
            intent.putExtra("pwd", password)
            intent.putExtra("cpwd", cpassword)
            intent.putExtra("addre", address)
            //intent.putExtra("otp",verificationId)
            startActivity(intent)
        }

    }
 /*   private fun verificationCallbacks() {
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(Credentials: PhoneAuthCredential) {
                Toast.makeText(
                    this@RegisterActivity,
                    "verification done$Credentials",
                    Toast.LENGTH_LONG
                ).show()
               //startActivity(Intent(this@RegisterActivity, OTP_Activity::class.java))
                 *//*verificationId= Credentials.smsCode.toString()
                println("Verification ID is in onVerificationCompleted is:"+verificationId)
                val message: String = name.getText().toString()
               println("name is : "+message)
                val intent = Intent(this@RegisterActivity, OTP_Activity::class.java)
                intent.putExtra("otp",verificationId)
                intent.putExtra("MESSAGE_KEY", message)
                startActivity(intent)*//*
            }
            override fun onVerificationFailed(e: FirebaseException) {
                println("exception is :" + e)
                Toast.makeText(this@RegisterActivity, e.message, Toast.LENGTH_LONG).show()

            }
            override fun onCodeSent(verfication: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verfication, token)
                verificationId=verfication
                Toast.makeText(this@RegisterActivity, "Verification code sent to mobile", Toast.LENGTH_LONG).show()
                //startActivity(Intent(this@RegisterActivity, OTP_Activity::class.java))
                resendToken = token
                val intent = Intent(this@RegisterActivity, OTP_Activity::class.java)
                val username: String = name.text.toString()
                val email:String=email.text.toString()
                val mobile: String = phnNo.text.toString()
                val password: String = password.text.toString()
                val cpassword: String = cpassword.text.toString()
                val address: String = address.text.toString()
                intent.putExtra("uname", username)
                intent.putExtra("mail", email)
                intent.putExtra("number", mobile)
                intent.putExtra("pwd", password)
                intent.putExtra("cpwd", cpassword)
                intent.putExtra("addre", address)
                intent.putExtra("otp",verificationId)
                startActivity(intent)
            }
        }
    }
    private fun verify() {
        verificationCallbacks()
        val phnNo = phnNo.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91" + phnNo,
            60,
            java.util.concurrent.TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            mCallbacks
        )
    }*/
}
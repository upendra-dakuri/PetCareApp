package com.omniwyse.petcaremobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.omniwyse.petcaremobileapp.model.Users
import com.omniwyse.petcaremobileapp.services.FactoryAPI
import com.omniwyse.petcaremobileapp.services.PetcareServices
import com.omniwyse.petcaremobileapp.services.RegisterResponse
import kotlinx.android.synthetic.main.activity_register.exist
import kotlinx.android.synthetic.main.activity_registration.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        name1.setText(intent.getStringExtra("name"))
        email1.setText(intent.getStringExtra("mail"))
                phnNo1.setText(intent.getStringExtra("number"))
                password1.setText(intent.getStringExtra("pwd"))
                cpassword1.setText(intent.getStringExtra("cpwd"))
                address1.setText(intent.getStringExtra("addre"))
        exist.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        register.setOnClickListener {
            registerUser(name1.text.toString(),email1.text.toString(),phnNo1.text.toString(),
                password1.text.toString(),cpassword1.text.toString(),address1.text.toString())
        }
    }

    private fun registerUser(
        name: String,
        email: String,
        phno: String,
        password: String,
        cPassword:String,
        address: String
    ) {

        val userinfo = Users(name,email,phno,password,cPassword,address)
        println(" user information "+userinfo);
        val petcare = FactoryAPI.buildService(PetcareServices::class.java)
        val requestCall: Call<RegisterResponse> = petcare.register(userinfo)
        requestCall.enqueue(object: Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                println("in success  body is :")
                println("response body is :"+response.body())
                if (response.isSuccessful) {
                    val msg = response.body().toString()
                    Toast.makeText(this@RegistrationActivity, msg, Toast.LENGTH_LONG).show()
                    println("success response " + response.body())
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                println("error in register is  : "+t)
                Toast.makeText(applicationContext,"In failure ",Toast.LENGTH_SHORT).show()
            }

        })

    }


}
package com.omniwyse.petcaremobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.omniwyse.petcaremobileapp.model.FPasswordBody
import com.omniwyse.petcaremobileapp.model.FPasswordResponse
import com.omniwyse.petcaremobileapp.services.FactoryAPI
import com.omniwyse.petcaremobileapp.services.PetcareServices
import kotlinx.android.synthetic.main.activity_reset_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetPasswordActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        val phnNo = intent.getStringExtra("mobile")
        submit1.setOnClickListener{
            reSetPassword(phnNo,password2.text.toString())
        }
    }
    private fun reSetPassword(
    phnNo:String,
    password2:String
    ){
        val info = FPasswordBody(phnNo,password2)
        println(" user information "+info);
        val petcare = FactoryAPI.buildService(PetcareServices::class.java)
        val requestCall: Call<FPasswordResponse> = petcare.reSettingPassword(info)
        requestCall.enqueue(object: Callback<FPasswordResponse> {
            override fun onResponse(call: Call<FPasswordResponse>, response: Response<FPasswordResponse>) {
                println("in  reset password success  body is :")
                println("response body is :"+response.body())
                if (response.isSuccessful) {
                    Toast.makeText(this@ResetPasswordActivity, "Password Reset Successful", Toast.LENGTH_LONG).show()
                    println("success response " + response.body())
                }
            }
            override fun onFailure(call: Call<FPasswordResponse>, t: Throwable) {
                println("error in reset password is  : "+t)
                Toast.makeText(applicationContext,"In failure ", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
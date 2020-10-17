package com.omniwyse.petcaremobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.omniwyse.petcaremobileapp.helpers.DestinationAdapter
import com.omniwyse.petcaremobileapp.model.Services
import com.omniwyse.petcaremobileapp.services.PetcareServices
import com.omniwyse.petcaremobileapp.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_list_of_organizations.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListOfOrganizations : AppCompatActivity() {
    var adapter: ArrayAdapter<*>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.omniwyse.petcaremobileapp.R.layout.activity_list_of_organizations)
        back.setOnClickListener {
            startActivity(Intent(this, HomeScreenActivity::class.java))
        }
        println("home request calling")
        val petcare = ServiceBuilder.buildService(PetcareServices::class.java)
        println("petcare is :" + petcare)
        val requestCall: Call<List<Services>> = petcare.getServices();
        println("request call is in list of organisations** :" + requestCall)
        requestCall.enqueue(object: Callback<List<Services>> {
            override fun onResponse(call: Call<List<Services>>, response: Response<List<Services>>) {
                if (response.isSuccessful) {
                        val destinationList = response.body()!!
                    println("destinationList1 " + response.body())
                       destiny_recycler_view.adapter = DestinationAdapter(destinationList)
                }

            }
            override fun onFailure(call: Call<List<Services>>?, t: Throwable?) {
                println("in error** " + t)
                Toast.makeText(applicationContext,"In failure ",Toast.LENGTH_SHORT).show()            }
        })
    }



}
package com.omniwyse.petcaremobileapp

import android.R.attr.thumb
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.omniwyse.petcaremobileapp.model.Services
import com.omniwyse.petcaremobileapp.services.PetcareServices
import com.omniwyse.petcaremobileapp.services.ServiceBuilder
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeScreenActivity : AppCompatActivity() {
    var sampleImages = intArrayOf(
        R.drawable.homedog,
        R.drawable.dog2,
        R.drawable.dog3
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        cardView1.setOnClickListener {
            startActivity(Intent(this,ListOfOrganizations::class.java))
        }

        val carouselView = findViewById(R.id.carouselView) as CarouselView;
        carouselView.setPageCount(sampleImages.size);
        carouselView.setImageListener(imageListener);
    }
    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            // You can use Glide or Picasso here
            imageView.setImageResource(sampleImages[position])
        }

    }
        /*println("home request calling")
        val petcare = ServiceBuilder.buildService(PetcareServices::class.java)
        println("petcare is :" + petcare)
        val requestCall: Call<List<Services>> = petcare.getServices();
        println("request call is :" + requestCall)
        requestCall.enqueue(object : Callback<List<Services>> {
            override fun onResponse(
                call: Call<List<Services>>?,
                response: Response<List<Services>>?
            ) {

                if (response != null) {
                    println("in response block2 " +response.body())
                    if (response.isSuccessful) {
                        val msg = response.body()
                        msg?.let {

                        }
                    } else {
                        Toast.makeText(
                            this@HomeScreenActivity,
                            "Its else block in response!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<Services>>?, t: Throwable?) {
                Toast.makeText(this@HomeScreenActivity, "Its Failure", Toast.LENGTH_SHORT).show()
            }
        })*/
       // cardView1.setOnClickListener { Intent(this,ListOfOrganizations::class.java) }

    }

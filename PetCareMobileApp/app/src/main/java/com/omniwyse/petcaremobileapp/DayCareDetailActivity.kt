package com.omniwyse.petcaremobileapp

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.omniwyse.petcaremobileapp.model.Services
import kotlinx.android.synthetic.main.activity_day_care_detail.*


class DayCareDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_care_detail)

        city.setMovementMethod(ScrollingMovementMethod())

        // setSupportActionBar(detail_toolbar)
        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val i = intent
        val listOfServices = i.getSerializableExtra("DAYCARESERVICE") as Services
        loadDetails(listOfServices)
    }


    private fun loadDetails(listOfServices: Services) {
    println(" in load details1 " + listOfServices)
       // city.listOfServices.organisation.phone
        city.setText(
            "About \n\n" + listOfServices.daycare.description +
                    "\n\nE-mail \n\n" + listOfServices.organisation.email +
                    "\n\nLocation \n\n " + listOfServices.organisation.location +
                    "\n\nPhone  \n\n" + listOfServices.organisation.phone +
                    "\n\n Timings  \n\n" + listOfServices.daycare.opentime.from +
                    "\n" + listOfServices.daycare.opentime.to + "\n\n Price :\n" + listOfServices.daycare.price
        )
        organizationName.setText(listOfServices.organisation.name)
    }
}
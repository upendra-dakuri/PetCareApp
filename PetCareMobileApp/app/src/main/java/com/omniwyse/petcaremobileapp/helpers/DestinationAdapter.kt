package com.omniwyse.petcaremobileapp.helpers

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omniwyse.petcaremobileapp.DayCareDetailActivity
import com.omniwyse.petcaremobileapp.R
import com.omniwyse.petcaremobileapp.model.Services

class DestinationAdapter(private val dayCareServiceDetailsList: List<Services>) :
    RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {
    private var images = intArrayOf(
        R.drawable.img,
        R.drawable.img,
        R.drawable.img,
        R.drawable.img,
        R.drawable.img,
        R.drawable.img
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_listview_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.dayCareServiceDetails = dayCareServiceDetailsList[position]
        holder.image.setImageResource(images[position])
        //holder.orgName_Phone.text = dayCareServiceDetailsList[position].organisation.name+"\r\n"+dayCareServiceDetailsList[position].organisation.phone
        println("organization name is " + dayCareServiceDetailsList[position].organisation.name)
        holder.orgName_Phone.text = dayCareServiceDetailsList[position].organisation.name
        holder.itemView.setOnClickListener { v ->
            val context = v.context
            val intent = Intent(context, DayCareDetailActivity::class.java)
            val service: Services = holder.dayCareServiceDetails!!
            println("service is " + service)
            intent.putExtra("DAYCARESERVICE", service)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dayCareServiceDetailsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val orgName_Phone: TextView = itemView.findViewById(R.id.label)
        val image: ImageView = itemView.findViewById(R.id.rimage)
        var dayCareServiceDetails: Services? = null
        override fun toString(): String {
            return """${super.toString()} '${orgName_Phone.text}'"""
        }
    }
}
package com.omniwyse.petcaremobileapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Services (
    var id: Int = 0,
    val zipcode: String,
    val organisation: Organisation,
    val daycare: Daycare
):Serializable {
    override fun toString(): String {
        return "Services(zipcode='$zipcode', organization=$organisation, daycare=$daycare)"
    }
}

data class Organisation(
    val name: String,
    val email: String,
    val phone: String,
    val location: String
):Serializable

data class Daycare(
    val opentime: Opentime,
    val price: Price,
    @SerializedName("FoodDetails") val fooddetails: FoodDetails,
    val description:String,
    val pictures:String,
    val additionalServices:String
):Serializable
data class Opentime(
    val from:String,
    val to:String
):Serializable
data class Price(
    val dogs:String,
    val cats:String
):Serializable
data class FoodDetails(
    @SerializedName("Dogs") val dogs: Dogs,
    @SerializedName("Cats")val cats: Cats
):Serializable
data class Dogs(
   @SerializedName("Breakfast") val breakfast: Breakfast,
   val lunch: Lunch,
   @SerializedName("Dinner") val dinner: Dinner
):Serializable
data class Breakfast(
    val food : String,
    val price:String
):Serializable
data class Lunch(
    val food : String,
    val price:String
):Serializable
data class Dinner(
    val food : String,
    val price:String
):Serializable
data class Cats(
    @SerializedName("Breakfast") val breakfast: Breakfast,
    val lunch: Lunch,
    @SerializedName("Dinner") val dinner: Dinner
):Serializable


package com.codingblocks.restapiretrofitjson.models
/**
 * Created by amandhapola on 01/07/17.
 */
data class User(var id:Int,var name:String,var username:String,var email:String,var address: Address,var phone:String,var website:String,var comapny:Company)

data class Address(var street:String, var suite:String, var city:String, var zipcode:String,var geo:Geo)

data class Geo(var lat:String,var lng:String)

data class Company(var name:String , var catchPhrase:String ,var bs:String)


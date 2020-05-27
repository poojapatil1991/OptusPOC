package com.example.optusdemo.userInfo.model
/*
* Model class for Adsress
 */

class Address {
    var street: String = ""
    var suite: String = ""
    var city: String = ""
    var zipcode: String = ""
    var geo: GeoLocation = GeoLocation()
}
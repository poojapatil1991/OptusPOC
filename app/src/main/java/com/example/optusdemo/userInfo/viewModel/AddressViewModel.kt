package com.example.optusdemo.userInfo.viewModel

import androidx.lifecycle.ViewModel

class AddressViewModel : ViewModel() {
    var street: String = " "
    var suite: String = " "
    var city: String = " "
    var zipcode: String = " "
    var geo: GeoLocationViewModel = GeoLocationViewModel()
}
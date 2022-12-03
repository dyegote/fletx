package com.diegot.services.api

import com.diegot.services.apimodel.HoldersVehicleResponse
import retrofit2.Call
import retrofit2.http.GET

interface VehicleService {

    @GET("/people/holder_vehicles/2282.json")
    fun getHolderVehicle(): Call<HoldersVehicleResponse>
}
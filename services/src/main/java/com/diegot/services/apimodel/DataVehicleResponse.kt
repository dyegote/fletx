package com.diegot.services.apimodel

import com.google.gson.annotations.SerializedName

class DataVehicleResponse(
    @SerializedName("placa") val placa: String,
    @SerializedName("lonlat") val lonlat: String,
    @SerializedName("front_vehicle") val frontVehicle: FrontVehicleResponse,
    @SerializedName("thirdstate") val thirdState: ThirdStateResponse,
    @SerializedName("driver") val driver: DriverResponse?,
    @SerializedName("trailer") val trailer: TrailerResponse?
)
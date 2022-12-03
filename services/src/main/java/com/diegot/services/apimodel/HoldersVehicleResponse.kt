package com.diegot.services.apimodel

import com.google.gson.annotations.SerializedName

class HoldersVehicleResponse(
    @SerializedName("data") val data: List<DataVehicleResponse>
)
package com.diegot.services.apimodel

import com.google.gson.annotations.SerializedName

class TrailerResponse(
    @SerializedName("id") val id: String,
    @SerializedName("placa") val placa: String
)
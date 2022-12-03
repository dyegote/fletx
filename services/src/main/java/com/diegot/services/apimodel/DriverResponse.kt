package com.diegot.services.apimodel

import com.google.gson.annotations.SerializedName

class DriverResponse(
    @SerializedName("id") val id: String,
    @SerializedName("thirdstates_id") val thirdStatesId: String,
    @SerializedName("full_name") val fullName: String
)
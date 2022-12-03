package com.diegot.services.apimodel

import com.google.gson.annotations.SerializedName

class ThirdStateResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String
) {
}
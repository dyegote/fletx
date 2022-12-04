package com.diegot.fletx.uimodel

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehiclesUiModel(
    val isLoading: Boolean = false,
    val vehicles: List<VehicleUiModel> = listOf()
) : Parcelable

@Parcelize
data class VehicleUiModel(
    val placa: String = "",
    val driverName: String = "",
    val status: String = "",
    val placaTrailer: String = "",
    val imageUrl: String = "",
    val location: LatLng = LatLng(0.0, 0.0)
) : Parcelable
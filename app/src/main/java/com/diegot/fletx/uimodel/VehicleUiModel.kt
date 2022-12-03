package com.diegot.fletx.uimodel

data class VehiclesUiModel(
    val isLoading: Boolean = false,
    val vehicles: List<VehicleUiModel> = listOf()
)

data class VehicleUiModel(
    val placa: String = "",
    val driverName: String = "",
    val status: String = "",
    val placaTrailer: String = "",
    val lonlat: String = ""
)
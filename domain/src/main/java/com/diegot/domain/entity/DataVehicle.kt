package com.diegot.domain.entity

class DataVehicle(
    val placa: String,
    val lonlat: String,
    val frontVehicle: FrontVehicle,
    val thirdState: ThirdState,
    val driver: Driver?,
    val trailer: Trailer?
)
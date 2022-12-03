package com.diegot.domain.repository

import com.diegot.domain.entity.DataVehicle

interface VehicleRepository {

    fun getHoldersVehicle(): List<DataVehicle>
}
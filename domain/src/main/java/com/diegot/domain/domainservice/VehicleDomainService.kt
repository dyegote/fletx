package com.diegot.domain.domainservice

import com.diegot.domain.entity.DataVehicle
import com.diegot.domain.repository.VehicleRepository
import javax.inject.Inject

class VehicleDomainService @Inject constructor(private val repository: VehicleRepository) {

    fun getHoldersVehicle(): List<DataVehicle> {
        val vehicles = repository.getHoldersVehicle().toMutableList()
        vehicles.forEach{
            it.frontVehicle.url = it.frontVehicle.url.replace("prueba", "staging")
        }
        return vehicles.toList()
    }
}
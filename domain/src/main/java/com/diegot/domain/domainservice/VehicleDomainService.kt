package com.diegot.domain.domainservice

import com.diegot.domain.entity.DataVehicle
import com.diegot.domain.repository.VehicleRepository
import javax.inject.Inject

class VehicleDomainService @Inject constructor(private val repository: VehicleRepository) {

    fun getHoldersVehicle(): List<DataVehicle> = repository.getHoldersVehicle()
}
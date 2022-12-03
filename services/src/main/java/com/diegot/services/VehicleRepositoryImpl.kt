package com.diegot.services

import com.diegot.domain.entity.Driver
import com.diegot.domain.entity.FrontVehicle
import com.diegot.domain.entity.DataVehicle
import com.diegot.domain.entity.ThirdState
import com.diegot.domain.entity.Trailer
import com.diegot.domain.repository.VehicleRepository
import com.diegot.services.api.ApiClient
import com.diegot.services.api.ApiErrorResponse
import com.diegot.services.api.ApiResponse
import com.diegot.services.api.ApiSuccessResponse
import com.diegot.services.apimodel.DriverResponse
import com.diegot.services.apimodel.FrontVehicleResponse
import com.diegot.services.apimodel.DataVehicleResponse
import com.diegot.services.apimodel.ThirdStateResponse
import com.diegot.services.apimodel.TrailerResponse

class VehicleRepositoryImpl : VehicleRepository {

    override fun getHoldersVehicle(): List<DataVehicle> {
        val response = ApiClient().getHolderVehicleService().getHolderVehicle().execute()
        return when(val apiResponse = ApiResponse.create(response)) {
            is ApiSuccessResponse -> {
                apiResponse.body?.data?.map {
                    it.toDomainModel()
                } ?: listOf()
            }
            is ApiErrorResponse -> {
                val exception = apiResponse as? ApiErrorResponse
                throw Exception(exception?.message)
            }
        }
    }

    private fun DataVehicleResponse.toDomainModel() = DataVehicle(
        placa,
        lonlat,
        frontVehicle.toDomainModel(),
        thirdState.toDomainModel(),
        driver?.toDomainModel(),
        trailer?.toDomainModel()
    )

    private fun FrontVehicleResponse.toDomainModel() = FrontVehicle(url)

    private fun ThirdStateResponse.toDomainModel() = ThirdState(id, name, status)

    private fun DriverResponse.toDomainModel() = Driver(id, thirdStatesId, fullName)

    private fun TrailerResponse.toDomainModel() = Trailer(id, placa)
}
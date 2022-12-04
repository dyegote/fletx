package com.diegot.fletx

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegot.domain.domainservice.VehicleDomainService
import com.diegot.domain.entity.DataVehicle
import com.diegot.fletx.uimodel.VehicleUiModel
import com.diegot.fletx.uimodel.VehiclesUiModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(private val domainService: VehicleDomainService): ViewModel() {

    private val _data = MutableLiveData(VehiclesUiModel())
    val data = _data as LiveData<VehiclesUiModel>

    fun getHoldersVehicle() {
        viewModelScope.launch(Dispatchers.Main){
            _data.value = data.value?.copy(isLoading = true)
            try {
                val vehicles =  withContext(Dispatchers.IO) { domainService.getHoldersVehicle() }
                _data.value = data.value?.copy(vehicles = vehicles.map { it.toUiModel() })
            } catch(ex: Exception) {
                ex.printStackTrace()
            }
            _data.value = data.value?.copy(isLoading = false)
        }
    }

    private fun DataVehicle.toUiModel(): VehicleUiModel{
        return VehicleUiModel(
            placa = placa,
            driverName = driver?.fullName ?: "Sin conductor",
            status = thirdState.name,
            placaTrailer = trailer?.placa ?: "Sin trailer",
            imageUrl = frontVehicle.url,
            location = parseLocation(lonlat)
        )
    }

    private fun parseLocation(lonlat: String): LatLng {
        val matcher = Regex("(-?[0-9]{1,13}(\\.[0-9]*)?)")
        val longitude = matcher.findAll(lonlat).elementAtOrNull(0)?.groupValues?.get(1)?.toDouble() ?: 0.0
        val latitude = matcher.findAll(lonlat).elementAtOrNull(1)?.groupValues?.get(1)?.toDouble() ?: 0.0
        return LatLng(latitude, longitude)
    }
}
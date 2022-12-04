package com.diegot.fletx.maps

import android.app.Activity
import android.view.View
import com.diegot.fletx.databinding.MapMarkerBinding
import com.diegot.fletx.uimodel.VehicleUiModel
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker


class MapMarkerAdapter(private val activity: Activity, private val vehicle: VehicleUiModel) : InfoWindowAdapter {

    private lateinit var binding: MapMarkerBinding

    override fun getInfoContents(marker: Marker): View? {
        binding = MapMarkerBinding.inflate(activity.layoutInflater)
        binding.textViewPlate.text = vehicle.placa
        binding.textViewName.text = vehicle.driverName
        binding.textViewTrailer.text = vehicle.placaTrailer
        binding.textViewStatus.text = vehicle.status
        return binding.root
    }

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }
}
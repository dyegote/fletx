package com.diegot.fletx.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegot.fletx.R
import com.diegot.fletx.uimodel.VehicleUiModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object{
        const val EXTRA_DATA_VEHICLE = "EXTRA_DATA_VEHICLE"
    }

    private var vehicleModel: VehicleUiModel? = null
    private lateinit var vehicleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        initializeData(savedInstanceState)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initializeData(savedInstanceState: Bundle?){
        savedInstanceState?.let {
            vehicleModel = it.getParcelable(EXTRA_DATA_VEHICLE)
        } ?: intent.let{
            vehicleModel = it.getParcelableExtra(EXTRA_DATA_VEHICLE)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        vehicleMap = googleMap
        vehicleModel?.let {
            vehicleMap.addMarker(
                MarkerOptions()
                    .position(it.location)
                    .title(it.placa))
            vehicleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(it.location, 10f))
            vehicleMap.setInfoWindowAdapter(MapMarkerAdapter(this, it)
            )
        }
    }
}
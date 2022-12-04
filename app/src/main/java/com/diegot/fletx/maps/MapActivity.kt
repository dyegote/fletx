package com.diegot.fletx.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.diegot.fletx.R
import com.diegot.fletx.uimodel.VehicleUiModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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
                    .icon(bitmapDescriptorFromVector(this, R.drawable.ic_vehicle_marker))
                    .position(it.location)
                    .title(it.placa)
            )
            vehicleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(it.location, 10f))
            vehicleMap.setInfoWindowAdapter(MapMarkerAdapter(this, it)
            )
        }
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}
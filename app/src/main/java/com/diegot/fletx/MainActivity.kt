package com.diegot.fletx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegot.fletx.databinding.ActivityMainBinding
import com.diegot.fletx.uimodel.VehicleUiModel
import com.diegot.fletx.uimodel.VehiclesUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: VehicleViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        VehicleAdapter(onItemClickListener = { vehicle -> onItemVehicleClick(vehicle)})
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initializeRecyclerView()
        initObserver()
        viewModel.getHoldersVehicle()
    }

    private fun initObserver() {
        viewModel.data.observe(this) { observeData(it) }
    }

    private fun observeData(data: VehiclesUiModel) {
        //binding.loadingView.isVisible = data.loading
        if(data.vehicles.isNotEmpty())
            showVehicles(data.vehicles)
    }

    private fun showVehicles(vehicles: List<VehicleUiModel>) {
        adapter.submitList(vehicles)
        if (binding.recyclerView.adapter == null) {
            binding.recyclerView.adapter = adapter
        }
    }

    private fun initializeRecyclerView() {
        val layoutManagerVehicles = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.apply {
            layoutManager = layoutManagerVehicles
        }
    }

    private fun onItemVehicleClick(vehicle: VehicleUiModel) {

    }
}
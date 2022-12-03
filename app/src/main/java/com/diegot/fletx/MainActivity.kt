package com.diegot.fletx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.diegot.fletx.databinding.ActivityMainBinding
import com.diegot.fletx.uimodel.VehiclesUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: VehicleViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initObserver()
        viewModel.getHoldersVehicle()
    }

    private fun initObserver() {
        viewModel.data.observe(this) { observeData(it) }
    }

    private fun observeData(data: VehiclesUiModel) {
        //binding.loadingView.isVisible = data.loading
    }
}
package com.diegot.fletx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diegot.fletx.databinding.ItemVehicleBinding
import com.diegot.fletx.uimodel.VehicleUiModel

class VehicleAdapter(private val onItemClickListener: (VehicleUiModel) -> Unit): ListAdapter<VehicleUiModel, RecyclerView.ViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  VehicleViewHolder(ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false).root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vehicleViewHolder = holder as VehicleViewHolder
        vehicleViewHolder.bind(getItem(position) as VehicleUiModel)
    }

    inner class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemVehicleBinding.bind(itemView)

        fun bind(vehicle: VehicleUiModel) {
            binding.root.setOnClickListener() {
                onItemClickListener(vehicle)
            }

            with(binding) {
                textViewPlate.text = vehicle.placa
                textViewName.text = vehicle.driverName
                textViewTrailerPlate.text = vehicle.placaTrailer
                textViewStatus.text = vehicle.status
            }

        }
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<VehicleUiModel>() {
        override fun areItemsTheSame(oldItem: VehicleUiModel, newItem: VehicleUiModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: VehicleUiModel, newItem: VehicleUiModel) =
            oldItem == newItem
    }
}

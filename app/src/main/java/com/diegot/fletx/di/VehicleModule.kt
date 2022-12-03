package com.diegot.fletx.di

import com.diegot.domain.domainservice.VehicleDomainService
import com.diegot.domain.repository.VehicleRepository
import com.diegot.services.VehicleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class VehicleModule {

    @Provides
    fun provideVehicleDomainService(repository: VehicleRepositoryImpl) : VehicleDomainService = VehicleDomainService(repository)
}
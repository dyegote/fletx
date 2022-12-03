package com.diegot.fletx.di

import com.diegot.domain.repository.VehicleRepository
import com.diegot.services.VehicleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class SinlgetonModule {

    @Provides
    fun provideVehicleRepository() : VehicleRepositoryImpl = VehicleRepositoryImpl()

}
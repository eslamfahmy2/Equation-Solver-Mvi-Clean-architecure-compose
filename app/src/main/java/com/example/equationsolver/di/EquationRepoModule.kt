package com.example.equationsolver.di

import com.example.equationsolver.data.repository.EquationRepositoryImpl
import com.example.equationsolver.domain.EquationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EquationRepoModule {

    @Binds
    abstract fun provideEquationIm(
        equationRepository : EquationRepositoryImpl
    ): EquationRepository

}
package com.example.android_highthon_10th.data.di


import com.example.android_highthon_10th.data.datasource.AuthRemoteDataSourceImpl
import com.example.android_highthon_10th.data.datasource.PersonaRemoteDataSourceImpl
import com.example.android_highthon_10th.data.datasource.SessionLocalDataSourceImpl
import com.example.android_highthon_10th.data.datasource.source.AuthRemoteDataSource
import com.example.android_highthon_10th.data.datasource.source.PersonaRemoteDataSource
import com.example.android_highthon_10th.data.datasource.source.SessionLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindAuthRemoteDataSource(
        dataSource: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindPersonaRemoteDataSource(
        dataSource: PersonaRemoteDataSourceImpl
    ): PersonaRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindSessionLocalDataSource(
        dataSource: SessionLocalDataSourceImpl
    ): SessionLocalDataSource

}
package com.example.android_highthon_10th.data.di

import com.example.android_highthon_10th.data.datasource.SessionLocalDataSourceImpl
import com.example.android_highthon_10th.data.repository.LoginRepositoryImpl
import com.example.android_highthon_10th.data.repository.SessionRepositoryImpl
import com.example.android_highthon_10th.data.repository.SignUpRepositoryImpl
import com.example.android_highthon_10th.domain.repository.LoginRepository
import com.example.android_highthon_10th.domain.repository.SessionRepository
import com.example.android_highthon_10th.domain.repository.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindLoginRepository(
        impl: LoginRepositoryImpl
    ): LoginRepository

    @Singleton
    @Binds
    abstract fun bindSignUpRepository(
        impl: SignUpRepositoryImpl
    ): SignUpRepository

    @Singleton
    @Binds
    abstract fun bindSessionRepository(
        impl: SessionRepositoryImpl
    ): SessionRepository

}
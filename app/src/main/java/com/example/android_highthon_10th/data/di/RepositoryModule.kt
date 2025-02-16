package com.example.android_highthon_10th.data.di

import com.example.android_highthon_10th.data.datasource.SessionLocalDataSourceImpl
import com.example.android_highthon_10th.data.repository.AlarmRepositoryImpl
import com.example.android_highthon_10th.data.repository.LoginRepositoryImpl
import com.example.android_highthon_10th.data.repository.PersonaRepositoryImpl
import com.example.android_highthon_10th.data.repository.SessionRepositoryImpl
import com.example.android_highthon_10th.data.repository.SignUpRepositoryImpl
import com.example.android_highthon_10th.data.repository.TodoRepositoryImpl
import com.example.android_highthon_10th.domain.repository.AlarmRepository
import com.example.android_highthon_10th.domain.repository.LoginRepository
import com.example.android_highthon_10th.domain.repository.PersonaRepository
import com.example.android_highthon_10th.domain.repository.SessionRepository
import com.example.android_highthon_10th.domain.repository.SignUpRepository
import com.example.android_highthon_10th.domain.repository.TodoRepository
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

    @Singleton
    @Binds
    abstract fun bindAlarmRepository(
        impl: AlarmRepositoryImpl
    ): AlarmRepository

    @Singleton
    @Binds
    abstract fun bindTodoRepository(
        impl: TodoRepositoryImpl
    ): TodoRepository


    @Singleton
    @Binds
    abstract fun bindPersonaRepository(
        impl: PersonaRepositoryImpl
    ): PersonaRepository
}
package com.example.android_highthon_10th.domain.di

import com.example.android_highthon_10th.domain.repository.AlarmRepository
import com.example.android_highthon_10th.domain.repository.LoginRepository
import com.example.android_highthon_10th.domain.repository.SessionRepository
import com.example.android_highthon_10th.domain.repository.SignUpRepository
import com.example.android_highthon_10th.domain.repository.TodoRepository
import com.example.android_highthon_10th.domain.usecase.AlarmUseCase
import com.example.android_highthon_10th.domain.usecase.LoginUseCase
import com.example.android_highthon_10th.domain.usecase.SignUpUseCase
import com.example.android_highthon_10th.domain.usecase.TodoUseCase
import com.example.android_highthon_10th.domain.usecase.ValidateEmailUseCase
import com.example.android_highthon_10th.domain.usecase.ValidateNameUseCase
import com.example.android_highthon_10th.domain.usecase.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideValidateEmailUseCase(): ValidateEmailUseCase {
        return ValidateEmailUseCase()
    }

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase(): ValidatePasswordUseCase {
        return ValidatePasswordUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateNameUseCase(): ValidateNameUseCase {
        return ValidateNameUseCase()
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        repository: SignUpRepository,
        session: SessionRepository
    ): SignUpUseCase {
        return SignUpUseCase(repository, session)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: LoginRepository,
        session: SessionRepository
    ): LoginUseCase {
        return LoginUseCase(repository, session)
    }

    @Provides
    @Singleton
    fun provideTodoUseCase(repository: TodoRepository): TodoUseCase {
        return TodoUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideAlarmUseCase(repository: AlarmRepository): AlarmUseCase {
        return AlarmUseCase(repository)
    }
}
package com.example.android_highthon_10th.data.di

import com.example.android_highthon_10th.BuildConfig
import com.example.android_highthon_10th.data.api.AuthApi
import com.example.android_highthon_10th.data.api.PersonaApi
import com.example.android_highthon_10th.data.api.UserApi
import com.example.android_highthon_10th.data.interceptor.AppInterceptor
import com.example.android_highthon_10th.data.interceptor.DefaultInterceptor
import com.example.android_highthon_10th.domain.repository.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DefaultRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AppRetrofit

    @Singleton
    @Provides
    fun provideAppInterceptor(
        sessionRepository: SessionRepository,
        authApi: AuthApi
    ): AppInterceptor {
        return AppInterceptor(sessionRepository, authApi)
    }

    @Singleton
    @Provides
    fun provideDefaultInterceptor(): DefaultInterceptor {
        return DefaultInterceptor()
    }

    @Singleton
    @Provides
    @DefaultRetrofit
    fun provideDefaultRetrofit(interceptor: DefaultInterceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @AppRetrofit
    fun provideAppRetrofit(interceptor: AppInterceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAuthApi(@DefaultRetrofit retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    fun provideUserApi(@AppRetrofit retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    fun providePersonaApi(@AppRetrofit retrofit: Retrofit): PersonaApi {
        return retrofit.create(PersonaApi::class.java)
    }

}
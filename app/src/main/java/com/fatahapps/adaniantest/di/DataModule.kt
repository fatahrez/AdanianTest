package com.fatahapps.adaniantest.di

import com.fatahapps.data.mappers.Mapper
import com.fatahapps.data.mappers.UserDomainDataMapper
import com.fatahapps.data.mappers.UserSuccessDomainDataMapper
import com.fatahapps.data.remote.EcobbaApi
import com.fatahapps.data.remote.HttpClient
import com.fatahapps.data.remote.HttpLogger
import com.fatahapps.data.remote.dto.UserDTO
import com.fatahapps.data.remote.dto.UserSuccessDTO
import com.fatahapps.data.repository.EcobbaRepositoryImpl
import com.fatahapps.domain.entities.UserEntity
import com.fatahapps.domain.entities.UserSuccessEntity
import com.fatahapps.domain.repository.EcobbaRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [DataModule.Binders::class])
@InstallIn(SingletonComponent::class)
class DataModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binders {
        @Binds
        fun bindsRepository(
            ecobbaRepositoryImpl: EcobbaRepositoryImpl
        ): EcobbaRepository

        @Binds
        fun bindsUserMapper(
            userDomainDataMapper: UserDomainDataMapper
        ): Mapper<UserEntity, UserDTO>

        @Binds
        fun bindsUserSuccessMapper(
            userSuccessDomainDataMapper: UserSuccessDomainDataMapper
        ): Mapper<UserSuccessEntity, UserSuccessDTO>
    }

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLogger.create()

    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return HttpClient.setupOkHttpClient(httpLoggingInterceptor)
    }

    @Singleton
    @Provides
    fun providesEcobbaApi(retrofit: Retrofit): EcobbaApi = retrofit.create(EcobbaApi::class.java)

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(EcobbaApi.BASE_URL)
            .build()
    }
}
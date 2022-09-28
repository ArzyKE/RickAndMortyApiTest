package com.example.rickandmortyapitest.di

import com.example.rickandmortyapitest.common.constants.Constants
import com.example.rickandmortyapitest.data.network.apiservices.CharacterApiServices
import com.example.rickandmortyapitest.data.network.apiservices.EpisodesApiServices
import com.example.rickandmortyapitest.data.network.apiservices.LocationApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun createOkHttpClient() =
        OkHttpClient().newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun createRetrofitClient(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideCharactersApiService(retrofit: Retrofit): CharacterApiServices =
        retrofit.create(CharacterApiServices::class.java)

    @Singleton
    @Provides
    fun provideLocationsApiService(retrofit: Retrofit): LocationApiServices =
        retrofit.create(LocationApiServices::class.java)

    @Singleton
    @Provides
    fun provideEpisodesApiService(retrofit: Retrofit): EpisodesApiServices =
        retrofit.create(EpisodesApiServices::class.java)
}
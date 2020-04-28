package com.example.pixabayapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiFactory {

    private const val PIXENDPOINT = "https://pixabay.com/"

    fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(PIXENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun getClientApi(): ClientApi =
        getRetrofit().create(ClientApi::class.java)

}
package com.example.pixabayapi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientApi {

    @GET("api/?key=3588873-8bb0e70fdfcef7f31eee25461&")
    fun getAllImages(@Query("q") q: String): Call<ImageResponse>
}
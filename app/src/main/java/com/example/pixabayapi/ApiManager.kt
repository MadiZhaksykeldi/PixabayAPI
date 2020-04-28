package com.example.pixabayapi

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager {
    fun loadImages(
        onSuccess: (ImageResponse) -> Unit,
        onError: (Throwable) -> Unit,
        keyword: String
    ) {
        ApiFactory.getClientApi()
            .getAllImages(keyword)
            .enqueue(object : Callback<ImageResponse> {

                override fun onResponse(
                    call: Call<ImageResponse>,
                    response: Response<ImageResponse>
                ) {
                    onSuccess(response.body()!!)
                }

                override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                    onError(t)
                }

            })
    }
}
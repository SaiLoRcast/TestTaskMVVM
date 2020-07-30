package com.example.mvvmtesttask.api

import com.example.mvvmtesttask.model.CardholdersResponse
import com.example.mvvmtesttask.model.ExchangeRatesResponse
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET("/daily_json.js")
    fun getExchangeRates(): Call<ExchangeRatesResponse>

    @GET("/test/android/v1/users.json")
    fun getCardholders(): Call<CardholdersResponse>

}
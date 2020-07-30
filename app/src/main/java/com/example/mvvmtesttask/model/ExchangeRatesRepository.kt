package com.example.mvvmtesttask.model

import com.example.mvvmtesttask.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExchangeRatesRepository {

    var exchangeRates: ExchangeRatesResponse? = null
    var cardholders: CardholdersResponse? = null

    fun getExchangeRates(onResult: (isSuccess: Boolean, response: ExchangeRatesResponse?) -> Unit) {

        ApiClient.exchangeRates.getExchangeRates().enqueue(object : Callback<ExchangeRatesResponse> {
            override fun onResponse(
                call: Call<ExchangeRatesResponse>?,
                response: Response<ExchangeRatesResponse>?
            ) {
                if (response != null && response.isSuccessful) {
                    exchangeRates = response.body()!!
                    onResult(true, exchangeRates)
                } else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<ExchangeRatesResponse>?, t: Throwable?) {
                onResult(false, null)
            }

        })
    }

    fun getCardholders(onResult: (isSuccess: Boolean, response: CardholdersResponse?) -> Unit) {

        ApiClient.cardholders.getCardholders().enqueue(object : Callback<CardholdersResponse> {
            override fun onResponse(
                call: Call<CardholdersResponse>?,
                response: Response<CardholdersResponse>?
            ) {
                if (response != null && response.isSuccessful) {
                    cardholders = response.body()!!
                    onResult(true, cardholders)
                } else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<CardholdersResponse>?, t: Throwable?) {
                onResult(false, null)
            }

        })
    }

    companion object {
        private var INSTANCE: ExchangeRatesRepository? = null
        fun getInstance() = INSTANCE
            ?: ExchangeRatesRepository().also {
                INSTANCE = it
            }
    }
}
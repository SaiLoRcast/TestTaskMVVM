package com.example.mvvmtesttask.view.ui.fragments.main_fragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.mvvmtesttask.model.CardholdersResponse
import com.example.mvvmtesttask.model.ExchangeRatesRepository
import com.example.mvvmtesttask.model.ExchangeRatesResponse
import com.example.mvvmtesttask.utils.ErrorHandler
import ru.polygalov.mvvm.view.base.BaseViewModel
import java.security.AccessControlContext


class MainFragmentViewModel : BaseViewModel() {

    val exchangeRates = MutableLiveData<ExchangeRatesResponse>()
    val cardholders = MutableLiveData<CardholdersResponse>()

    fun updateExchangeRates(context: Context) {
        dataLoading.value = true
        ExchangeRatesRepository.getInstance().getExchangeRates { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                exchangeRates.value = response
                empty.value = false
            } else {
//                ErrorHandler.handleError(error, context)
                empty.value = true
            }
        }
    }

    fun updateCardholders() {
        dataLoading.value = true
        ExchangeRatesRepository.getInstance().getCardholders { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                cardholders.value = response
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }

}
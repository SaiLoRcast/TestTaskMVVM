package com.example.mvvmtesttask.view.ui.fragments.main_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.mvvmtesttask.R
import com.example.mvvmtesttask.model.CardholdersResponse
import com.example.mvvmtesttask.model.ExchangeRatesResponse
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MainFragmentViewModel()
        viewModel.updateExchangeRates(requireContext())
        viewModel.updateCardholders()
        updateExchangeRates()
        card.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_cardsList)
        }
//        swipe_to_refresh_data.setOnRefreshListener {
//            updateExchangeRates()
//            swipe_to_refresh_data.isRefreshing = false
//        }
    }

    private fun updateExchangeRates() {
        viewModel.exchangeRates.observe(viewLifecycleOwner, Observer {
            updateExchangeRates(it)
        })

        viewModel.cardholders.observe(viewLifecycleOwner, Observer {
            updateCardholders(it)
        })

    }

    private fun updateCardholders(cardholdersResponse: CardholdersResponse) {
        card_number.text = cardholdersResponse.users[0].card_number
        cardholder_name.text = cardholdersResponse.users[0].cardholder_name
        card_valid.text = cardholdersResponse.users[0].valid
        card_balance.text = String.format("$%1s", cardholdersResponse.users[0].balance.toString())


    }

    private fun updateExchangeRates(exchangeRatesResponse: ExchangeRatesResponse?) {
        Toast.makeText(context, "A{DATE", Toast.LENGTH_SHORT).show()
    }
}
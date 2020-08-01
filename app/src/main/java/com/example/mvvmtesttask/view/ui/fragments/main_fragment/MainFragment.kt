package com.example.mvvmtesttask.view.ui.fragments.main_fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmtesttask.R
import com.example.mvvmtesttask.adapters.HistoryListAdapter
import com.example.mvvmtesttask.model.CardholdersResponse
import com.example.mvvmtesttask.model.ExchangeRatesResponse
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainFragmentViewModel
    private var cardholdersResponse: CardholdersResponse? = null
    private var exchangeRatesResponse: ExchangeRatesResponse? = null
    private lateinit var adapter: HistoryListAdapter

    private var position: Int = 0
    private var selectedExchangeRate: Int = 0

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

        lifecycleScope.launch {
            val operation = async(Dispatchers.IO) {
                
            }
            operation.await()

            delay(300)
            // update views
            updateHistoryList(exchangeRatesResponse!!.Valute.get("USD")!!.Value/ exchangeRatesResponse!!.Valute.get("GBP")!!.Value)
            gbp.performClick()
        }

        val sharedPreference =
            requireContext().getSharedPreferences("position", Context.MODE_PRIVATE)
        position = sharedPreference.getInt("position", 0)

        card.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("cardholders", cardholdersResponse)
            Navigation.findNavController(view)
                .navigate(R.id.action_mainFragment_to_cardsList, bundle)
        }

        gbp.setOnClickListener {
            balance_in_currency.text = String.format("£ %1s", getInCurrency(cardholdersResponse!!.users[position].balance, exchangeRatesResponse!!.Valute.get("USD")!!.Value/ exchangeRatesResponse!!.Valute.get("GBP")!!.Value))
            setupViews(gbp)
            selectedExchangeRate = 1
            updateHistoryList(exchangeRatesResponse!!.Valute.get("USD")!!.Value/ exchangeRatesResponse!!.Valute.get("GBP")!!.Value)
        }
        eur.setOnClickListener {
            balance_in_currency.text = String.format("€ %1s", getInCurrency(cardholdersResponse!!.users[position].balance, exchangeRatesResponse!!.Valute.get("USD")!!.Value/ exchangeRatesResponse!!.Valute.get("EUR")!!.Value))
            setupViews(eur)
            selectedExchangeRate = 2
            updateHistoryList(exchangeRatesResponse!!.Valute.get("USD")!!.Value/ exchangeRatesResponse!!.Valute.get("EUR")!!.Value)
        }

        rub.setOnClickListener {
            balance_in_currency.text = String.format("₽ %1s", getInCurrency(cardholdersResponse!!.users[position].balance, exchangeRatesResponse!!.Valute.get("USD")!!.Value))
            setupViews(rub)
            selectedExchangeRate = 3
            updateHistoryList(exchangeRatesResponse!!.Valute.get("USD")!!.Value)
        }

    }

    private fun setupViews(view: LinearLayout?) {
        when (view) {
            gbp-> {
                gbp.setBackgroundResource(R.color.active_background)
                eur.setBackgroundResource(R.color.color_white)
                rub.setBackgroundResource(R.color.color_white)
                gbp_sign.setTextColor(resources.getColor(R.color.color_white))
                eur_sign.setTextColor(resources.getColor(R.color.text_smoke))
                rub_sign.setTextColor(resources.getColor(R.color.text_smoke))
                gbp_name.setTextColor(resources.getColor(R.color.color_white))
                eur_name.setTextColor(resources.getColor(R.color.text_smoke))
                rub_name.setTextColor(resources.getColor(R.color.text_smoke))
            }
            eur-> {
                gbp.setBackgroundResource(R.color.color_white)
                eur.setBackgroundResource(R.color.active_background)
                rub.setBackgroundResource(R.color.color_white)
                gbp_sign.setTextColor(resources.getColor(R.color.text_smoke))
                eur_sign.setTextColor(resources.getColor(R.color.color_white))
                rub_sign.setTextColor(resources.getColor(R.color.text_smoke))
                gbp_name.setTextColor(resources.getColor(R.color.text_smoke))
                eur_name.setTextColor(resources.getColor(R.color.color_white))
                rub_name.setTextColor(resources.getColor(R.color.text_smoke))
            }
            rub-> {
                gbp.setBackgroundResource(R.color.color_white)
                eur.setBackgroundResource(R.color.color_white)
                rub.setBackgroundResource(R.color.active_background)
                gbp_sign.setTextColor(resources.getColor(R.color.text_smoke))
                eur_sign.setTextColor(resources.getColor(R.color.text_smoke))
                rub_sign.setTextColor(resources.getColor(R.color.color_white))
                gbp_name.setTextColor(resources.getColor(R.color.text_smoke))
                eur_name.setTextColor(resources.getColor(R.color.text_smoke))
                rub_name.setTextColor(resources.getColor(R.color.color_white))
            }

        }

    }

    private fun getInCurrency(balance: Float, exchangeRate:Float): Float {
        return balance * exchangeRate
    }

    private fun updateExchangeRates() {
        viewModel.exchangeRates.observe(viewLifecycleOwner, Observer { it ->
            exchangeRatesResponse = it

//            updateHistoryList(exchangeRatesResponse.Valute.get("USD")!!.Value/ exchangeRatesResponse.Valute.get("GBP")!!.Value)
//            gbp.performClick()
        })

        viewModel.cardholders.observe(viewLifecycleOwner, Observer {
            updateCardholders(it)
            cardholdersResponse = it

        })
    }

    private fun updateHistoryList(exchangeRate: Float) {
        adapter = HistoryListAdapter(
            cardholdersResponse!!.users[position].transaction_history,
            selectedExchangeRate,
            exchangeRate
        )
        val layoutManager = LinearLayoutManager(context)
        history_list.layoutManager = layoutManager
        history_list.adapter = adapter
    }

    private fun updateCardholders(cardholdersResponse: CardholdersResponse) {
        card_number.text = cardholdersResponse.users[position].card_number
        cardholder_name.text = cardholdersResponse.users[position].cardholder_name
        card_valid.text = cardholdersResponse.users[position].valid
        card_balance.text =
            String.format("$%1s", cardholdersResponse.users[position].balance.toString())
        balance_in_currency.text = String.format("£ %1s", cardholdersResponse.users[position].balance.toString())

        when (cardholdersResponse.users[position].type) {
            "mastercard" -> card_logo.setImageResource(R.drawable.ic_master_card_logo)
            "visa" -> card_logo.setImageResource(R.drawable.ic_visa_logo)
            "unionpay" -> card_logo.setImageResource(R.drawable.ic_unionpay_logo)
        }

    }
}
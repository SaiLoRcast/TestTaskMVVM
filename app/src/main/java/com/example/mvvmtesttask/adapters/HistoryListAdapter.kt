package com.example.mvvmtesttask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtesttask.R
import com.example.mvvmtesttask.model.TransactionHistory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.transaction_history_item.view.*
import kotlin.math.round

class HistoryListAdapter(
    transactionHistory: List<TransactionHistory>,
    private var selectedExchangeRate: Int,
    private var exchangeRate: Float
) :

    RecyclerView.Adapter<HistoryListAdapter.EmployeeListViewHolder>() {
    private var transactionHistoryList = transactionHistory


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EmployeeListViewHolder(inflater, parent)
    }

    override fun getItemCount() = transactionHistoryList.size

    override fun onBindViewHolder(holder: EmployeeListViewHolder, position: Int) {
        holder.setup(transactionHistoryList[position], selectedExchangeRate,exchangeRate)
    }

    class EmployeeListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.transaction_history_item, parent, false)) {

        private val transactionImage: ImageView = itemView.transaction_image
        private val transactionName: TextView = itemView.transaction_name
        private val transactionDate: TextView = itemView.transaction_date
        private val transactionCostInCurrency: TextView = itemView.transaction_cost_in_currency
        private val transactionCost: TextView = itemView.transaction_cost

        fun setup(
            itemData: TransactionHistory,
            selectedExchangeRate: Int,
            exchangeRate: Float
        ) {

            Picasso.with(itemView.context)
                .load(itemData.icon_url)
                .into(transactionImage)

            transactionName.text = itemData.title
            transactionDate.text = itemData.date
            transactionCost.text = String.format("$ %1s", itemData.amount)

            when (selectedExchangeRate) {
                1-> transactionCostInCurrency.text = String.format("£ %.2f %n", getInCurrency(itemData.amount.toFloat(), exchangeRate))
                2-> transactionCostInCurrency.text = String.format("€ %.2f %n", getInCurrency(itemData.amount.toFloat(), exchangeRate))
                3-> transactionCostInCurrency.text = String.format("₽ %.2f %n", getInCurrency(itemData.amount.toFloat(), exchangeRate))

            }

        }

        private fun getInCurrency(balance: Float, exchangeRate:Float): Float {
            return balance * exchangeRate
        }
    }

}
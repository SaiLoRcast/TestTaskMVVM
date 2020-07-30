package com.example.mvvmtesttask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtesttask.R
import com.example.mvvmtesttask.model.Cardholders
import kotlinx.android.synthetic.main.cardholders_item.view.*

class CardholdersListAdapter(private val cardholders: List<Cardholders>) :

    RecyclerView.Adapter<CardholdersListAdapter.EmployeeListViewHolder>() {
    var cardholdersList = cardholders

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EmployeeListViewHolder(inflater, parent)
    }

    override fun getItemCount() = cardholdersList.size

    override fun onBindViewHolder(holder: EmployeeListViewHolder, position: Int) {
        holder.setup(cardholdersList[position])
    }

    class EmployeeListViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.cardholders_item, parent, false)) {

        val cardLogo: ImageView = itemView.card_logo
        val cardNumber: TextView = itemView.card_number

        fun setup(itemData: Cardholders) {

            when (itemData.type) {
                "mastercard" -> cardLogo.setImageResource(R.drawable.ic_master_card_logo)
                "visa" -> cardLogo.setImageResource(R.drawable.ic_visa_logo)
                "unionpay" -> cardLogo.setImageResource(R.drawable.ic_unionpay_logo)
            }

            cardNumber.text = itemData.card_number

//            itemView.setOnClickListener() {
//                itemView.findNavController()
//                    .navigate(R.id.action_employeeListFragment_to_employeeDetailFragment)
//            }
        }
    }

}
package com.example.mvvmtesttask.view.ui.fragments.cardholders_list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmtesttask.R
import com.example.mvvmtesttask.adapters.CardholdersListAdapter
import com.example.mvvmtesttask.model.CardholdersResponse
import kotlinx.android.synthetic.main.fragment_cards_list.*

class CardsListFragment : Fragment() {

    var cardholdersResponse: CardholdersResponse? = null
    private lateinit var adapter: CardholdersListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cards_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardholdersResponse = requireArguments().getSerializable("cardholders") as CardholdersResponse?
        adapter = CardholdersListAdapter(cardholdersResponse!!.users)
        val layoutManager = LinearLayoutManager(context)
        cardholders_list.layoutManager = layoutManager
        cardholders_list.adapter = adapter
    }
}
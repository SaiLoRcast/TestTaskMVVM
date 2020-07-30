package com.example.mvvmtesttask.model


data class ExchangeRatesResponse(

    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute: HashMap<String, Valute>
)

data class Valute(
    val ID: String,
    val NumCode: String,
    val CharCode: String,
    val Nominal: Int,
    val Name: String,
    val Value: Float,
    val Previous: Float
)

data class CardholdersResponse(
    val users: List<Cardholders>
)

data class Cardholders(
    val card_number: String,
    val type: String,
    val cardholder_name: String,
    val valid: String,
    val balance: Float,
    val transaction_history: List<TransactionHistory>
)

data class TransactionHistory(
    val title: String,
    val icon_url: String,
    val date: String,
    val amount: String
)





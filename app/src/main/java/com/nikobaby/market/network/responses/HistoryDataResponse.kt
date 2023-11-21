package com.nikobaby.market.network.responses

import com.google.gson.annotations.SerializedName
import com.nikobaby.market.data.vos.LiveVO

data class HistoryDataResponse (
    @SerializedName("child")
    val child : List<LiveVO>?,

    @SerializedName("date")
    val date : String?
)
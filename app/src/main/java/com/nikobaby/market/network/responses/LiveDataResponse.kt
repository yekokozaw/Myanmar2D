package com.nikobaby.market.network.responses

import com.google.gson.annotations.SerializedName
import com.nikobaby.market.data.vos.LiveVO

data class LiveDataResponse(

    @SerializedName("live")
    val live : LiveVO?,

    @SerializedName("result")
    val result : List<LiveVO>?
)

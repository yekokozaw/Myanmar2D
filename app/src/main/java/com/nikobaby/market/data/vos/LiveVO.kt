package com.nikobaby.market.data.vos
import com.google.gson.annotations.SerializedName

data class LiveVO(

    @SerializedName("set")
    val set : String?,

    @SerializedName("value")
    val value : String?,

    @SerializedName("time")
    val time: String? = " ",

    @SerializedName("twod")
    val twod : String? = " ",

    @SerializedName("open_time")
    val openTime : String? = " "

)

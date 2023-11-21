package com.nikobaby.market.data.vos

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MorningResultVO(

    var set : String? = "",
    var twoD : String? = "",
    var value : String? = ""
)

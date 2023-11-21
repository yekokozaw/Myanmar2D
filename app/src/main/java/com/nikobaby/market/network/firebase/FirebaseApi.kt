package com.nikobaby.market.network.firebase

import com.nikobaby.market.data.vos.MorningResultVO

interface FirebaseApi {
    fun getMorningResult(
        onSuccess: (MorningResultVO) -> Unit,
        onFailure : (String) ->Unit
    )

    fun setMorningResult(
        set : String,
        value : String,
        twoD : String
    )
}
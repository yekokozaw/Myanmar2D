package com.nikobaby.market.data.model

import com.nikobaby.market.data.vos.LiveVO
import com.nikobaby.market.data.vos.MorningResultVO
import com.nikobaby.market.network.firebase.FirebaseApi
import com.nikobaby.market.network.responses.HistoryDataResponse
import com.nikobaby.market.network.responses.LiveDataResponse

interface MarketModel {

    var mFirebaseApi : FirebaseApi

    fun get2DLive(
        onSuccess : (LiveDataResponse) -> Unit,
        onFailure : (String) -> Unit
    )

    fun get2DHistory(
        onSuccess: (List<HistoryDataResponse>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMorningResult(
        onSuccess: (MorningResultVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun setMorningResult(
        set : String,
        value : String,
        twoD : String
    )
}

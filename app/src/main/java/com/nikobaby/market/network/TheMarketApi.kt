package com.nikobaby.market.network

import com.nikobaby.market.network.responses.HistoryDataResponse
import com.nikobaby.market.network.responses.LiveDataResponse
import com.nikobaby.market.utils.API_GET_HISTORY
import com.nikobaby.market.utils.API_GET_LIVE
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface TheMarketApi {

    @GET(API_GET_LIVE)
    fun get2dLiveData(

    ): Observable<LiveDataResponse>

    @GET(API_GET_HISTORY)
    fun get2dHistoryData(

    ): Observable<List<HistoryDataResponse>>
}
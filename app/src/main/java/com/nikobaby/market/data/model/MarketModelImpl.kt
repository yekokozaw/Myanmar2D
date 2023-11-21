package com.nikobaby.market.data.model

import android.annotation.SuppressLint
import com.nikobaby.market.data.vos.LiveVO
import com.nikobaby.market.data.vos.MorningResultVO
import com.nikobaby.market.network.firebase.FirebaseApi
import com.nikobaby.market.network.firebase.RealtimeDatabaseApiImpl
import com.nikobaby.market.network.responses.HistoryDataResponse
import com.nikobaby.market.network.responses.LiveDataResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object MarketModelImpl :BaseModel() , MarketModel {

    override var mFirebaseApi: FirebaseApi = RealtimeDatabaseApiImpl

    @SuppressLint("CheckResult")
    override fun get2DLive(onSuccess: (LiveDataResponse) -> Unit, onFailure: (String) -> Unit) {
        mMarketApi.get2dLiveData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 -> onSuccess(it1) }
            },{
                it.localizedMessage?.let { it1 -> onFailure(it1) }
            })
    }

    @SuppressLint("CheckResult")
    override fun get2DHistory(
        onSuccess: (List<HistoryDataResponse>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMarketApi.get2dHistoryData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            },{
                it.localizedMessage?.let { it1 -> onFailure(it1) }
            })
    }

    override fun getMorningResult(onSuccess: (MorningResultVO) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getMorningResult(
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun setMorningResult(set: String, value: String, twoD: String) {
        mFirebaseApi.setMorningResult(set,value,twoD)
    }
}
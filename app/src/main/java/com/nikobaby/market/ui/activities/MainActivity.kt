package com.nikobaby.market.ui.activities

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nikobaby.market.data.model.MarketModel
import com.nikobaby.market.data.model.MarketModelImpl
import com.nikobaby.market.data.vos.LiveVO
import com.nikobaby.market.databinding.ActivityMainBinding
import com.nikobaby.market.network.responses.LiveDataResponse
import com.nikobaby.market.utils.getMyanmarTimeZone
import com.nikobaby.market.utils.getTimes
import com.nikobaby.market.utils.getTodayName
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val handler = Handler()
    private lateinit var mActivityMainBinding: ActivityMainBinding
    private var mMarketModelImpl : MarketModel = MarketModelImpl
    private val myanmarTimeZone = getMyanmarTimeZone()
    private val currentTimeInMyanmar: Date = Calendar.getInstance(myanmarTimeZone).time
    private val morningTime = getTimes().first
    private val eveningTime = getTimes().second

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mActivityMainBinding.root
        setContentView(view)

        val today = getTodayName()
        if (today == "Sunday" || today == "Saturday"){
            val holiday = "$today Holiday "
            mActivityMainBinding.appBarMain.tvAppBar.text = holiday
        }else
            mActivityMainBinding.appBarMain.tvAppBar.text = today

        setUpListeners()

        getDataFromFirebase()
        startNetworkCall()
    }

    private fun getDataFromFirebase() {
        if (currentTimeInMyanmar.time < eveningTime)
            startTwinklingEffect()

        if (currentTimeInMyanmar.time > morningTime){
            mMarketModelImpl.getMorningResult(
                onSuccess = {
                    mActivityMainBinding.tvSetMorning.text = it.set
                    mActivityMainBinding.tvValueMorning.text = it.value
                    mActivityMainBinding.tvFinalResultMorning.text = it.twoD
                },
                onFailure = { Toast.makeText(this,it,Toast.LENGTH_SHORT).show()}
            )
        }
    }

    private fun setUpListeners() {
        mActivityMainBinding.appBarMain.appBarCalendar.setOnClickListener {
            startActivity(ResultHistoryActivity.newIntent(this))
        }
    }

    private fun networkCall() {
        if(currentTimeInMyanmar.time > eveningTime){
            mActivityMainBinding.ivCheckLoading.visibility = View.GONE
            mActivityMainBinding.ivCheckUpdated.visibility = View.VISIBLE
        }
        mMarketModelImpl.get2DLive(
            onSuccess = {
                bindData(it)
                it.result?.let { it1 -> setUpMorningEvening(it1) }

            },
            onFailure = {
                Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun bindData(it: LiveDataResponse) {
        val liveData = it.live
        mActivityMainBinding.tvLiveTwoD.text = liveData?.twod ?: "__"
        mActivityMainBinding.tvUpdatedDateTime.text = liveData?.time ?: "__"

        if(currentTimeInMyanmar.time<morningTime){
            mActivityMainBinding.tvSetMorning.text = liveData?.set ?: "__"
            mActivityMainBinding.tvValueMorning.text = liveData?.value ?: "__"
        }
        else if(currentTimeInMyanmar.time == morningTime){
            mMarketModelImpl.setMorningResult(
                liveData?.set ?: "",
                liveData?.value ?: "",
                liveData?.twod ?: "")
        }
        else if (currentTimeInMyanmar.time in (morningTime + 1) until eveningTime){
            mActivityMainBinding.tvSetEvening.text = liveData?.set ?: "__"
            mActivityMainBinding.tvValueEvening.text = liveData?.value ?: "__"
        }
    }

    private fun setUpMorningEvening(mResult: List<LiveVO>){

        for( result in mResult){
            when(result.openTime){

                "16:30:00" ->{
                    if (currentTimeInMyanmar.time >= eveningTime){
                        mActivityMainBinding.tvSetEvening.text = result.set
                        mActivityMainBinding.tvValueEvening.text = result.value
                    }
                    mActivityMainBinding.tvFinalResultEvening.text = result.twod
                }
            }
        }
    }

    private fun startNetworkCall(){
        val network = object : Runnable {
            override fun run() {
                networkCall()
                handler.postDelayed(this,3000)
            }
        }
        handler.post(network)
    }
    private fun startTwinklingEffect() {
        // Use a Handler to create a twinkling effect for the TextView
        val twinklingRunnable = object : Runnable {
            override fun run() {
                toggleVisibilityWithAnimation(mActivityMainBinding.tvLiveTwoD)
                handler.postDelayed(this, 2000) // Run every second
            }
        }
        handler.post(twinklingRunnable)
    }

    private fun toggleVisibilityWithAnimation(view: View) {
        // Use AlphaAnimation to create a twinkling effect
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 1000 // Fade in duration
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.duration = 500 // Fade out duration

        // Combine fade in and fade out animations
        val animation = AnimationSet(true)
        animation.addAnimation(fadeIn)
        animation.addAnimation(fadeOut)

        view.startAnimation(animation)
    }

    override fun onDestroy() {
        handler.looper.quit()
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
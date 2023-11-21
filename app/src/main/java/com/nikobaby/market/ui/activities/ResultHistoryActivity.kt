package com.nikobaby.market.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikobaby.market.R
import com.nikobaby.market.data.model.MarketModel
import com.nikobaby.market.data.model.MarketModelImpl
import com.nikobaby.market.databinding.ActivityResultHistoryBinding
import com.nikobaby.market.ui.adapters.ResultHistoryAdapter

class ResultHistoryActivity : AppCompatActivity() {

    private lateinit var mAdapter : ResultHistoryAdapter
    private lateinit var mActivityResultHistoryBinding: ActivityResultHistoryBinding
    private val mMarketModelImpl : MarketModel = MarketModelImpl

    companion object {
        fun newIntent(context : Context): Intent{
            return Intent(context,ResultHistoryActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityResultHistoryBinding = ActivityResultHistoryBinding.inflate(layoutInflater)
        val historyView = mActivityResultHistoryBinding.root
        setContentView(historyView)

        setUpRecyclerView()
        networkCall()

    }

    private fun networkCall(){
        mMarketModelImpl.get2DHistory(
            onSuccess = {
                mAdapter.setNewData(it)
            },
            onFailure = {
                Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpRecyclerView(){
        mAdapter = ResultHistoryAdapter()
        mActivityResultHistoryBinding.rvHistory.adapter = mAdapter
        mActivityResultHistoryBinding.rvHistory.layoutManager =
            LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
    }
}
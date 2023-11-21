package com.nikobaby.market.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikobaby.market.databinding.ViewHolderHistoryBinding
import com.nikobaby.market.network.responses.HistoryDataResponse
import com.nikobaby.market.ui.viewholder.ResultHistoryViewHolder

class ResultHistoryAdapter : RecyclerView.Adapter<ResultHistoryViewHolder>() {
    private var mHistoryData : List<HistoryDataResponse> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHistoryViewHolder {
       val binding = ViewHolderHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ResultHistoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ResultHistoryViewHolder, position: Int) {
        if (mHistoryData.isNotEmpty()){
            holder.bindData(mHistoryData[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(data : List<HistoryDataResponse>){
        mHistoryData = data
        notifyDataSetChanged()
    }
}
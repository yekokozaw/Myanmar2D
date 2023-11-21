package com.nikobaby.market.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nikobaby.market.databinding.ViewHolderHistoryBinding
import com.nikobaby.market.network.responses.HistoryDataResponse

class ResultHistoryViewHolder(private val binding: ViewHolderHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(vData : HistoryDataResponse){
        binding.tvtv.text = vData.date
        val results = vData.child
        if (results != null) {
            for (result in results){
                when(result.time){
                    "12:00:00" -> binding.tvFinalResultMorning.text = result.twod
                    "16:30:00" -> binding.tvFinalResultEvening.text = result.twod
                }
            }
        }
    }
}
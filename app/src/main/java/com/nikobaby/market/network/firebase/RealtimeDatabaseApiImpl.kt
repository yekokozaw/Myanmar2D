package com.nikobaby.market.network.firebase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nikobaby.market.data.vos.MorningResultVO

object RealtimeDatabaseApiImpl : FirebaseApi {

    private val database = FirebaseDatabase.getInstance().reference

    override fun getMorningResult(onSuccess: (MorningResultVO) -> Unit, onFailure: (String) -> Unit) {
        database.child("morningResult").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue(MorningResultVO::class.java)?.let{
                    onSuccess(it)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

        })
    }

    override fun setMorningResult(set: String, value: String, twoD: String) {
        database.child("morningResult").setValue(MorningResultVO(set,twoD,value))
    }
}
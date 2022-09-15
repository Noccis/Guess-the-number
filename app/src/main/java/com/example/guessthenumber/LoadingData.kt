package com.example.guessthenumber

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

object LoadingData {

    /*init {
        CoroutineScope(Dispatchers.IO).launch {
            createMockData()
        }
    }*/

    val TAG = "tag"

    private val listOfRandomNumbers = mutableListOf<Int>()

    suspend fun createMockData(){
        for (i in 1..20000){
            val number = (1..1000).random()
            listOfRandomNumbers.add(number)
            Log.d(TAG, "createMockData: Added nr $number")
        }
        Log.d(TAG, "createMockData: Finished!")
    }
}
package com.example.guessthenumber

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.log

object LoadingData {

    // Singelton loading with coroutine to not block the main thread.
    val TAG = "tag"

    private val listOfRandomNumbers = mutableListOf<Int>()
    private val myJob = Job()
    private val myScope = CoroutineScope(Dispatchers.IO + myJob)

    fun createMockData(){

        myScope.launch {
            for (i in 1..200000){
                val number = (1..1000).random()
                listOfRandomNumbers.add(number)
                Log.d(TAG, "createMockData: Added nr $number")
            }
            Log.d(TAG, "createMockData: Finished!")
        }
    }

    fun stopData(){
        myJob.cancel()
    }

}
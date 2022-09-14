package com.example.guessthenumber

import android.util.Log

object RandomNrGenerator {
    var activeNr = 0
    var diff = "easy"
    val TAG = "tag"

    fun generateRandomNr(){
        when(diff) {
            "easy" -> activeNr = (1..10).random()
            "normal" -> activeNr = (1..100).random()
            "hard" -> activeNr = (1..1000).random()
            else -> {
                Log.d(TAG, "generateRandomNr: nr is $activeNr")
            }
        }
    }

    fun setDifficulty(lvl: String) {
        if (lvl == "easy" || lvl == "normal" || lvl == "hard"){
            diff = lvl
        }else{
            Log.d(TAG, "setDifficulty: ERROR!")
        }
    }

    fun checkAnswer(nr: Int): String{
        if (nr == activeNr){
            return "You guessed right! The number was $activeNr"
        }else if (nr != activeNr && nr < activeNr){
            return "Nope. The number is higher then $nr"
        }else if(nr != activeNr && nr > activeNr){
            return "Nope. The number is lower then $nr"
        }
        return "checkAnswer ERROR nr = $nr"
    }
}
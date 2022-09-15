package com.example.guessthenumber

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel(){

    private val _activeNumber = MutableLiveData<Int>()
    val activeNumber: LiveData<Int> = _activeNumber

    private val _instructionsText = MutableLiveData<String>()
    val instructionsText: LiveData<String> = _instructionsText

    private val _timesGuessedText = MutableLiveData<String>()
    val timesGuessedText: LiveData<String> = _timesGuessedText

    private var numberOfTimesGuessed = 0
    
    private var difficulty = "easy"

    val TAG = "tag"

    init {
        generateRandomNr()
        _instructionsText.value = "Please select difficulty below."
        _timesGuessedText.value = "Number of times Guessed: $numberOfTimesGuessed"
        viewModelScope.launch(Dispatchers.IO){
            LoadingData.createMockData()
        }

    }

    fun generateRandomNr(){
        when(difficulty){
            "easy" -> _activeNumber.value = (1..10).random()
            "normal"-> _activeNumber.value = (1..100).random()
            "hard" -> _activeNumber.value = (1..1000).random()
            else -> {
                Log.d(TAG, "generateRandomNr: ERROR. diff is unknown")
            }
        }
        _instructionsText.value = "Guess the number!"
        Log.d(TAG, "RANDOM NR IS ${activeNumber.value}")
    }

    fun setDifficulty(lvl: String) {
        if (lvl == "easy" || lvl == "normal" || lvl == "hard"){
            difficulty = lvl
            Log.d(TAG, "Lvl is $lvl")
        }
    }

    fun setInfoTextView(nr: Int){
        _instructionsText.value = checkAnswer(nr)
    }

    fun checkAnswer(nr: Int): String{
        if (_activeNumber.value != null){
            if (nr == _activeNumber.value){
                return "You guessed right! The number was ${activeNumber.value}"
            }else if (nr != _activeNumber.value && nr < _activeNumber.value!!){
                increaseNumberOfTimesGuessed()
                return "Nope. The number is higher then $nr"
            }else if(nr != _activeNumber.value && nr > _activeNumber.value!!){
                increaseNumberOfTimesGuessed()
                return "Nope. The number is lower then $nr"
            }
        }
        return "checkAnswer ERROR nr = $nr"
    }

    fun increaseNumberOfTimesGuessed() {
        numberOfTimesGuessed++
        _timesGuessedText.value = "Number of times Guessed: $numberOfTimesGuessed"
    }
}

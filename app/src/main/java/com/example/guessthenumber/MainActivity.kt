package com.example.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvInfo: TextView
    private lateinit var tfInput: EditText
    private lateinit var randomNr: String
    private var playerInput = ""
    private var playerTimesGuessed = 0
    val TAG = "tag"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInfo = findViewById(R.id.tvInfo)
        tvInfo.text = "Please select difficulty below."
        val tvTimesGuessed = findViewById<TextView>(R.id.tvTimesGuessed)
        tvTimesGuessed.text = "Times guessed:"


        // Generates a random nr via our singelton
        randomNr = RandomNrGenerator.generateRandomNr().toString()
      //  var tvRandomNr = findViewById<TextView>(R.id.tvRandomNr)
       // tvRandomNr.text = "?"

        // Initializing our difficulty buttons
        val btnEasy = findViewById<Button>(R.id.btnEasy)
        btnEasy.setOnClickListener {
            setRandomNr("easy")
        }
        val btnNormal = findViewById<Button>(R.id.btnNormal)
        btnNormal.setOnClickListener {
            setRandomNr("normal")
        }

        val btnHard = findViewById<Button>(R.id.btnHard)
        btnHard.setOnClickListener {
            setRandomNr("hard")
        }

        // User input
        tfInput = findViewById(R.id.etInput)
        val btnSendInput = findViewById<Button>(R.id.btnSendInput)
        btnSendInput.setOnClickListener {
            getInput()
            playerTimesGuessed++
            tvTimesGuessed.text = "Times guessed: $playerTimesGuessed"
        }

    }

    private fun setRandomNr(lvl:String){
        RandomNrGenerator.setDifficulty("lvl")
        RandomNrGenerator.generateRandomNr()
        tvInfo.text = "Guess the number!"
    }
    private fun getInput(){
        Log.d(TAG, "getInput: RUNNING")
        if (tfInput.text != null){
            playerInput = tfInput.text.toString()
            Log.d(TAG, "getInput: Input = $playerInput")
            setInfoTextView(playerInput.toInt())
        }else{
            Log.d(TAG, "getInput: Input is empty.")
        }

    }

    private fun setInfoTextView(nr: Int){
        tvInfo.text = RandomNrGenerator.checkAnswer(nr)

    }
}
package com.example.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var tvInfo: TextView
    private lateinit var tfInput: EditText
    private lateinit var randomNr: String
    private var playerInput = ""
    private var playerTimesGuessed = 0
    private val TAG = "tag"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       /* CoroutineScope(Dispatchers.IO).launch {
            LoadingData.createMockData()
        }*/

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        tvInfo = findViewById(R.id.tvInfo)
        viewModel.instructionsText.observe(this, Observer {
            tvInfo.text = it.toString()
        })

        val tvTimesGuessed = findViewById<TextView>(R.id.tvTimesGuessed)
        viewModel.timesGuessedText.observe(this, Observer {
            tvTimesGuessed.text = it.toString()
        })

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
            playerHasAnswered()
            tfInput.setText("")
        }

    }

    private fun setRandomNr(lvl: String) {
        viewModel.setDifficulty(lvl)
        viewModel.generateRandomNr()
    }

    private fun playerHasAnswered() {
        Log.d(TAG, "getInput: RUNNING")
        if (tfInput.text.isNotEmpty()) {
            playerInput = tfInput.text.toString()
            Log.d(TAG, "playerHasAnswered: Input = $playerInput")
            viewModel.setInfoTextView(playerInput.toInt())
        } else {
            val toast = Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
        }
    }
}

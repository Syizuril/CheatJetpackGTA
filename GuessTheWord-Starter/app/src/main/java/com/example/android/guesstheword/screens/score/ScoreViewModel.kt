package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * Created by Syekh Syihabuddin Azmil Umri on 08/03/2021.
 */
class ScoreViewModel(finalScore: Int): ViewModel() {
    var score = finalScore

    init {
        Log.i("ScoreViewModel", "Final score is $finalScore")
    }
}
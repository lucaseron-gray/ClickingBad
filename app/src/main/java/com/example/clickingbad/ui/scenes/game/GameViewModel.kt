package com.example.clickingbad.ui.scenes.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import com.example.clickingbad.storage.ClickingBadDatabase
import kotlinx.coroutines.Dispatchers

class GameViewModel(application: Application) :
    AndroidViewModel(application) {

    private val playerDataDb = ClickingBadDatabase.getInstance(application).playerDataDAO()

    init {
        updateDisplay()
    }

    // Cook button clicked
    fun onButtonCookClicked() {
        TODO("Not yet implemented")
    }

    // Sell button clicked
    fun onButtonSellClicked() {
        TODO("Not yet implemented")
    }

    private fun updateDisplay() {
        TODO("Not yet implemented")
    }

    // coroutines to return livedata list
    val playerDataList = liveData(Dispatchers.IO) {
        // post value
        emit(playerDataDb.getPlayerData())
    }

}

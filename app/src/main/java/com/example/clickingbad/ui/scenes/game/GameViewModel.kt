package com.example.clickingbad.ui.scenes.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.clickingbad.storage.ClickingBadDatabase
import kotlinx.coroutines.Dispatchers

class GameViewModel(application: Application) :
    AndroidViewModel(application) {

    private val playerDataDb = ClickingBadDatabase.getInstance(application).playerDataDAO()

    // coroutines to return livedata list
    val playerDataList = liveData(Dispatchers.IO) {
        // post value
        emit(playerDataDb.getPlayerData())
    }

}

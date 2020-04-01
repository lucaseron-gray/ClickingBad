package com.example.clickingbad.ui.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.clickingbad.storage.ClickingBadDatabase
import kotlinx.coroutines.Dispatchers

class SharedViewModel(application: Application): AndroidViewModel(application) {

    private val playerDataDb = ClickingBadDatabase.getInstance(application).playerDataDAO()

    val dataList = liveData(Dispatchers.IO) {
        emit(playerDataDb.getPlayerData())
    }

}
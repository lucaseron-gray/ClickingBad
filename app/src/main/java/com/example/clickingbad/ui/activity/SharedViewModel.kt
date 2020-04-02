package com.example.clickingbad.ui.activity

import android.app.Application
import androidx.lifecycle.*
import com.example.clickingbad.business_logic.models.PlayerData
import com.example.clickingbad.storage.ClickingBadDatabase
import kotlinx.coroutines.*

enum class Purity(val type: String) {

}

class SharedViewModel(application: Application) :
    AndroidViewModel(application) {

    private val playerDataDb = ClickingBadDatabase.getInstance(application).playerDataDAO()
    val livePlayerData = MutableLiveData<PlayerData>()

    init {
        viewModelScope.launch {
            livePlayerData.value = playerDataDb.getPlayerData()
            startTick()
        }
    }

    override fun onCleared() {
        updateDB()
        super.onCleared()
    }

    // Tick function
    private fun startTick() {
        viewModelScope.launch {
            while (isActive) {
                tick()
                delay(100)
            }
        }
    }
    private fun tick() {
        livePlayerData.postValue(livePlayerData.value)
    }

    // Cook button clicked
    fun onButtonCookClicked() {
        livePlayerData.value?.let {
            it.batchAmount++
        }
    }
    // Sell button clicked
    fun onButtonSellClicked() {
        if (livePlayerData.value?.batchAmount ?: 0 > 0) {
            livePlayerData.value?.let {
                it.cashAmount += it.batchPrice
                it.batchAmount--
            }
        }
    }

    // Update DB
    // call 'updateDB()'
    private fun updateDB() {
        GlobalScope.launch (Dispatchers.IO) {
            livePlayerData.value?.let {
                playerDataDb.updateData(it)
            }
        }
    }
}

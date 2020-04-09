package com.example.clickingbad.ui.activity

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.clickingbad.business_logic.models.DistributionItem
import com.example.clickingbad.business_logic.models.LaunderingItem
import com.example.clickingbad.business_logic.models.ManufacturingItem
import com.example.clickingbad.business_logic.models.PlayerData
import com.example.clickingbad.storage.ClickingBadDatabase
import com.example.clickingbad.utils.startCoroutineTimer
import kotlinx.coroutines.*
import java.util.*

const val tickMilli: Long = 1000

class SharedViewModel(application: Application) :
    AndroidViewModel(application) {

    private val database = ClickingBadDatabase.getInstance(application)

    val livePlayerData = MutableLiveData<PlayerData>()
    val saveStatus = MutableLiveData<Boolean?>()

    private val maLive = MutableLiveData<List<ManufacturingItem>>()
    private val diLive = MutableLiveData<List<DistributionItem>>()
    private val laLive = MutableLiveData<List<LaunderingItem>>()

    private var thisTick: Long = 0
    private var lastTick: Long = 0

    private var makeAmount: Long = 0
    private var sellAmount: Long = 0
    private var launderAmount: Long = 0

    init {
        viewModelScope.launch {
            livePlayerData.value = database.playerDataDAO().getPlayerData()
            lastTick = livePlayerData.value?.lastTick!!

            calcConstants()
            delay(1000)
            startTick()
        }
        // save game every 30 seconds
        startCoroutineTimer(30000, 30000) {
            updateDB()
            saveStatus.postValue(true)
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            maLive.value = database.manufacturingDao().getUnlockedManufacturing()
            diLive.value = database.distributionDao().getUnlockedDistribution()
            laLive.value = database.launderingDao().getUnlockedLaundering()
            calcConstants()
        }
    }

    private fun calcConstants() {
        makeAmount = 0
        sellAmount = 0
        launderAmount = 0


        maLive.value?.forEach {
            makeAmount += it.amount * it.rps
        }
        diLive.value?.forEach {
            sellAmount += it.amount * it.rps
        }
        laLive.value?.forEach {
            launderAmount += it.amount * it.rps
        }

    }

    // Tick function
    private fun startTick() {
        viewModelScope.launch {
            while (isActive) {
                tick()
                delay(tickMilli)
            }
        }
    }

    private fun tick() {
        thisTick = Calendar.getInstance().timeInMillis
        lastTick = thisTick

        /*
        doLaunder((launderAmount/thisSub) * ticks!!)
         */

        doMake(makeAmount)
        doSell(sellAmount)

        livePlayerData.postValue(livePlayerData.value)
    }

    private fun doMake(value: Long) {
        livePlayerData.value?.let {
            it.batchAmount += value
        }
    }

    private fun doSell(value: Long) {
        var n = value
        livePlayerData.value?.let {
            if (it.batchAmount < 1) return
            if (n > it.batchAmount) {
                n += (it.batchAmount - n)
                if (n < 1) return
            }
            it.batchAmount -= n
            it.cashAmount += n * it.batchPrice
        }
    }

    // Cook button clicked
    fun onButtonCookClicked() {
        livePlayerData.value?.let {
            it.batchAmount++
        }
        livePlayerData.postValue(livePlayerData.value)
    }

    // Sell button clicked
    fun onButtonSellClicked() {
        if (livePlayerData.value?.batchAmount ?: 0 > 0) {
            livePlayerData.value?.let {
                it.cashAmount += it.batchPrice
                it.batchAmount--
            }
        }
        livePlayerData.postValue(livePlayerData.value)
    }

    // Update DB
    // call 'updateDB()'
    private fun updateDB() {
        Log.i("ViewModel", "UpdateDB Called!")
        viewModelScope.launch(Dispatchers.IO) {
            livePlayerData.value?.let {
                database.playerDataDAO().updateData(it)
                Log.i("ViewModel", "Room Update Called!")
            }
        }
    }
}

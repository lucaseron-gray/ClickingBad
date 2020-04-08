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

const val tickMilli: Long = 100
const val thisSub = 10

class SharedViewModel(application: Application) :
    AndroidViewModel(application) {

    private val database = ClickingBadDatabase.getInstance(application)

    val livePlayerData = MutableLiveData<PlayerData>()
    val saveStatus = MutableLiveData<Boolean?>()

    private var thisTick: Long? = null
    private var lastTick: Long? = null
    private var ticks: Long? = null

    private var makeAmount: Long = 0
    private var sellAmount: Long = 0
    private var launderAmount: Long = 0

    init {
        viewModelScope.launch {
            livePlayerData.value = database.playerDataDAO().getPlayerData()
            lastTick = livePlayerData.value?.lastTick

            getUnlocked()
            startTick()
        }
        // save game every 30 seconds
        startCoroutineTimer(30000, 30000) {
            updateDB()
            saveStatus.postValue(true)
        }
    }

    private fun getUnlocked() {
        viewModelScope.launch {
            val unlockedManufacturing = database.manufacturingDao().getUnlockedManufacturing()
            val unlockedDistribution = database.distributionDao().getUnlockedDistribution()
            val unlockedLaundering = database.launderingDao().getUnlockedLaundering()

            // default é 1 unlocked em cada
            calcConstants(unlockedManufacturing, unlockedDistribution, unlockedLaundering)
        }
    }

    private fun calcConstants(
        maList: List<ManufacturingItem>,
        diList: List<DistributionItem>,
        laList: List<LaunderingItem>
    ) {
        makeAmount = 0
        sellAmount = 0
        launderAmount = 0

        maList.forEach {
            makeAmount += it.amount * it.rps
        }
        diList.forEach {
            sellAmount += it.amount * it.rps
        }
        laList.forEach {
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
        ticks = (thisTick!! - lastTick!!) / tickMilli
        lastTick = thisTick as Long

        /*
        doLaunder((launderAmount/thisSub) * ticks!!)
         */

        doMake((makeAmount/thisSub) * ticks!!)
        doSell((sellAmount/thisSub) * ticks!!)

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
        Log.i("ViewModel", "UpdateDB Called!")
        viewModelScope.launch(Dispatchers.IO) {
            livePlayerData.value?.let {
                database.playerDataDAO().updateData(it)
                Log.i("ViewModel", "Room Update Called!")
            }
        }
    }
}

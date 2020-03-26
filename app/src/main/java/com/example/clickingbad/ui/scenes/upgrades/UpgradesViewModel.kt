package com.example.clickingbad.ui.scenes.upgrades

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.clickingbad.storage.ClickingBadDatabase
import com.example.clickingbad.storage.ManufacturingDAO
import kotlinx.coroutines.Dispatchers

class UpgradesViewModel(application: Application) :
    AndroidViewModel(application) {

    private val upgradesDb = ClickingBadDatabase.getInstance(application).upgradesDao()

    // coroutines to return livedata list
    val upgradesList = liveData(Dispatchers.IO) {
        // post value
        emit(upgradesDb.getUpgrades())
    }

}

package com.example.clickingbad.ui.scenes.distribution

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.clickingbad.storage.ClickingBadDatabase
import kotlinx.coroutines.Dispatchers

class DistributionViewModel(application: Application) :
    AndroidViewModel(application) {

    private val distributionDb = ClickingBadDatabase.getInstance(application).distributionDao()

    // coroutines to return livedata list
    val distributionList = liveData(Dispatchers.IO) {
        // post value
        emit(distributionDb.getDistribution())
    }

}

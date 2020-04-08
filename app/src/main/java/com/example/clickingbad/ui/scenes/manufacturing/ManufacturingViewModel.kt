package com.example.clickingbad.ui.scenes.manufacturing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.clickingbad.storage.ClickingBadDatabase
import kotlinx.coroutines.Dispatchers

class ManufacturingViewModel(application: Application) :
    AndroidViewModel(application) {

    private val manufacturingDb = ClickingBadDatabase.getInstance(application).manufacturingDao()

    // coroutines to return livedata list
    val manufacturingList = liveData(Dispatchers.IO) {
        // post value
        emit(manufacturingDb.getManufacturing())
    }

}

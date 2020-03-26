package com.example.clickingbad.ui.scenes.laundering

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.clickingbad.storage.ClickingBadDatabase
import kotlinx.coroutines.Dispatchers

class LaunderingViewModel(application: Application) :
    AndroidViewModel(application) {

    private val launderingDb = ClickingBadDatabase.getInstance(application).launderingDao()

    // coroutines to return livedata list
    val launderingList = liveData(Dispatchers.IO) {
        // post value
        emit(launderingDb.getLaundering())
    }

}

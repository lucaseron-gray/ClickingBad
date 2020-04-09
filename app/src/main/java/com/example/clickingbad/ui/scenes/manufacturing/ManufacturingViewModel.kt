package com.example.clickingbad.ui.scenes.manufacturing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.clickingbad.business_logic.models.ManufacturingItem
import com.example.clickingbad.storage.ClickingBadDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ManufacturingViewModel(application: Application) :
    AndroidViewModel(application) {

    private val manufacturingDb = ClickingBadDatabase.getInstance(application).manufacturingDao()
    val liveManufacturingDb = MutableLiveData<List<ManufacturingItem>>()

    init {
        viewModelScope.launch {
            liveManufacturingDb.value = manufacturingDb.getManufacturing()
        }
    }

    fun updateManufacturingList(obj: ManufacturingItem) {
        obj.amount++
        viewModelScope.launch {
            manufacturingDb.updateData(obj)
            liveManufacturingDb.value = manufacturingDb.getManufacturing()
//            manufacturingDb.updateAll(liveManufacturingDb.value!!)
        }
    }

}

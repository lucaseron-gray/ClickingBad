package com.example.clickingbad.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.clickingbad.business_logic.models.ManufacturingItem

@Dao
abstract class ManufacturingDAO {

    @Insert
    abstract fun insertData(data: List<ManufacturingItem>?)

    @Query(
        "SELECT * FROM cb_manufacturing_table ORDER BY baseCost ASC"
    )
    abstract suspend fun getManufacturing(): List<ManufacturingItem>

}
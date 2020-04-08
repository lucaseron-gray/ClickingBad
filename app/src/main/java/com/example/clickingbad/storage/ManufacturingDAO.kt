package com.example.clickingbad.storage

import androidx.room.*
import com.example.clickingbad.business_logic.models.ManufacturingItem

@Dao
abstract class ManufacturingDAO {

    @Insert
    abstract fun insertData(data: List<ManufacturingItem>?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateData(data: List<ManufacturingItem>)

    @Query(
        "SELECT * FROM cb_manufacturing_table ORDER BY baseCost ASC"
    )
    abstract suspend fun getManufacturing(): List<ManufacturingItem>

    @Query(
        "SELECT * FROM cb_manufacturing_table WHERE unlocked = 1"
    )
    abstract suspend fun getUnlockedManufacturing(): List<ManufacturingItem>

}

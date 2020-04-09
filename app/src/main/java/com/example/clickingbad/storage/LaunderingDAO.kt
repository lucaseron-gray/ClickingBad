package com.example.clickingbad.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.clickingbad.business_logic.models.LaunderingItem

@Dao
abstract class LaunderingDAO {

    @Insert
    abstract fun insertData(data: List<LaunderingItem>?)

    @Query(
        "SELECT * FROM cb_laundering_table ORDER BY baseCost ASC"
    )
    abstract suspend fun getLaundering(): List<LaunderingItem>

    @Query(
        "SELECT * FROM cb_laundering_table WHERE unlocked = 1"
    )
    abstract suspend fun getUnlockedLaundering(): List<LaunderingItem>

    @Query(
        "SELECT * FROM cb_laundering_table WHERE unlocked = 1"
    )
    abstract fun getObservable(): LiveData<List<LaunderingItem>>

}

package com.example.clickingbad.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.clickingbad.business_logic.models.DistributionItem

@Dao
abstract class DistributionDAO {

    @Insert
    abstract fun insertData(data: List<DistributionItem>?)

    @Query(
        "SELECT * FROM cb_distribution_table ORDER BY baseCost ASC"
    )
    abstract suspend fun getDistribution(): List<DistributionItem>

}
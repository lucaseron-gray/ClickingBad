package com.example.clickingbad.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.clickingbad.business_logic.models.UpgradesItem

@Dao
abstract class UpgradesDAO {

    @Insert
    abstract fun insertData(data: List<UpgradesItem>?)

    @Query(
        "SELECT * FROM cb_upgrades_table ORDER BY purchased DESC, cost ASC"
    )
    abstract suspend fun getUpgrades(): List<UpgradesItem>

}
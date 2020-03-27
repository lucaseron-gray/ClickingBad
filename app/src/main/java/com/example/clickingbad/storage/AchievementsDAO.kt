package com.example.clickingbad.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.clickingbad.business_logic.models.AchievementsItem

@Dao
abstract class AchievementsDAO {

    @Insert
    abstract fun insertData(data: List<AchievementsItem>?)

    @Query(
        "SELECT * FROM cb_achievements_table"
    )
    abstract suspend fun getAchievements(): List<AchievementsItem>

}
package com.example.clickingbad.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.clickingbad.business_logic.models.PlayerStats

@Dao
abstract class PlayerStatsDAO {

    @Insert
    abstract fun insertData(data: PlayerStats?)

    @Query(
        "SELECT * FROM cb_player_stats_table"
    )
    abstract suspend fun getPlayerStats(): PlayerStats

}
package com.example.clickingbad.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.clickingbad.business_logic.models.PlayerData

@Dao
abstract class PlayerDataDAO {

    @Insert
    abstract fun insertData(data: PlayerData?)

    @Query(
        "SELECT * FROM cb_player_data_table"
    )
    abstract suspend fun getPlayerData(): PlayerData

}
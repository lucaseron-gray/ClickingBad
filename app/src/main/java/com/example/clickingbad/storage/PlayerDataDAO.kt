package com.example.clickingbad.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.clickingbad.business_logic.models.PlayerData

@Dao
abstract class PlayerDataDAO {

    @Insert
    abstract fun insertData(data: PlayerData)

    @Update
    abstract suspend fun updateData(data: PlayerData)

    @Query(
        "SELECT * FROM cb_player_data_table"
    )
    abstract suspend fun getPlayerData(): PlayerData

}
package com.example.clickingbad.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.clickingbad.business_logic.models.EventsItem

@Dao
abstract class EventsDAO {

    @Insert
    abstract fun insertData(data: List<EventsItem>?)

    @Query(
        "SELECT * FROM cb_events_table"
    )
    abstract suspend fun getEvents(): List<EventsItem>

}
package com.example.clickingbad.storage

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.clickingbad.business_logic.models.*
import com.example.clickingbad.utils.SqlFunctions
import com.example.clickingbad.utils.fetchJson

@Database(
    entities = [ManufacturingItem::class, DistributionItem::class, LaunderingItem::class, UpgradesItem::class, AchievementsItem::class, EventsItem::class, PlayerData::class, PlayerStats::class],
    version = 10,
    exportSchema = false
)
abstract class ClickingBadDatabase : RoomDatabase() {

    abstract fun manufacturingDao(): ManufacturingDAO
    abstract fun distributionDao(): DistributionDAO
    abstract fun launderingDao(): LaunderingDAO
    abstract fun upgradesDao(): UpgradesDAO
    abstract fun achievementsDao(): AchievementsDAO
    abstract fun eventsDao(): EventsDAO
    abstract fun playerDataDAO(): PlayerDataDAO
    abstract fun playerStatsDAO(): PlayerStatsDAO

    companion object {

        private const val DATABASE_NAME = "clicking_bad.db"

        @Volatile
        private var instance: ClickingBadDatabase? = null

        fun getInstance(context: Context): ClickingBadDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context.applicationContext).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ClickingBadDatabase {
            return Room.databaseBuilder(
                    context.applicationContext,
                    ClickingBadDatabase::class.java,
                    DATABASE_NAME
                )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        val jason = fetchJson(context.applicationContext)
                        for (element in jason.manufacturing!!) {
                            db.execSQL(SqlFunctions.getSqlManufacturing(element))
                        }
                        for (element in jason.distribution!!) {
                            db.execSQL(SqlFunctions.getSqlDistribution(element))
                        }
                        for (element in jason.laundering!!) {
                            db.execSQL(SqlFunctions.getSqlLaundering(element))
                        }
                        for (element in jason.upgrades!!) {
                            db.execSQL(SqlFunctions.getSqlUpgrades(element))
                        }
                        for (element in jason.achievements!!) {
                            db.execSQL(SqlFunctions.getSqlAchievements(element))
                        }
                        for (element in jason.events!!) {
                            db.execSQL(SqlFunctions.getSqlEvents(element))
                        }

                        db.execSQL(SqlFunctions.getSqlPlayerData(jason.data!!))
                        db.execSQL(SqlFunctions.getSqlPlayerStats(jason.stats!!))
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
        }

    }

}

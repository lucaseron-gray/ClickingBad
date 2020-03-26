package com.example.clickingbad.storage

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.clickingbad.business_logic.models.DistributionItem
import com.example.clickingbad.business_logic.models.LaunderingItem
import com.example.clickingbad.business_logic.models.ManufacturingItem
import com.example.clickingbad.business_logic.models.UpgradesItem
import com.example.clickingbad.utils.fetchJson

@Database(
    entities = [ManufacturingItem::class, DistributionItem::class, LaunderingItem::class, UpgradesItem::class],
    version = 2,
    exportSchema = false
)
abstract class ClickingBadDatabase : RoomDatabase() {

    abstract fun manufacturingDao(): ManufacturingDAO
    abstract fun distributionDao(): DistributionDAO
    abstract fun launderingDao(): LaunderingDAO
    abstract fun upgradesDao(): UpgradesDAO

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
                            db.execSQL(getSqlManufacturing(element))
                        }
                        for (element in jason.distribution!!) {
                            db.execSQL(getSqlDistribution(element))
                        }
                        for (element in jason.laundering!!) {
                            db.execSQL(getSqlLaundering(element))
                        }
                        for (element in jason.upgrades!!) {
                            db.execSQL(getSqlUpgrades(element))
                        }

                    }
                })
                .fallbackToDestructiveMigration()
                .build()
        }

        private fun Boolean.toInt() = if (this) 1 else 0

        private fun getSqlManufacturing(data: ManufacturingItem): String {
            return "INSERT OR ABORT INTO `cb_manufacturing_table` " +
                    "(`unlockRps`,`amount`,`cost`,`rps`,`baseCost`,`description`,`risk`,`label`,`id`,`unlocked`) " +
                    "VALUES (${data.unlockRps},${data.amount},${data.cost},${data.rps},${data.baseCost},'${data.description}',${data.risk},'${data.label}','${data.id}',${data.unlocked!!.toInt()})"
        }

        private fun getSqlDistribution(data: DistributionItem): String {
            return "INSERT OR ABORT INTO `cb_distribution_table` " +
                    "(`unlockRps`,`amount`,`cost`,`rps`,`baseCost`,`description`,`risk`,`label`,`id`,`unlocked`) " +
                    "VALUES (${data.unlockRps},${data.amount},${data.cost},${data.rps},${data.baseCost},'${data.description}',${data.risk},'${data.label}','${data.id}',${data.unlocked!!.toInt()})"
        }

        private fun getSqlLaundering(data: LaunderingItem): String {
            return "INSERT OR ABORT INTO `cb_laundering_table` " +
                    "(`unlockRps`,`amount`,`cost`,`rps`,`baseCost`,`description`,`label`,`id`,`unlocked`) " +
                    "VALUES (${data.unlockRps},${data.amount},${data.cost},${data.rps},${data.baseCost},'${data.description}','${data.label}','${data.id}',${data.unlocked!!.toInt()})"
        }

        private fun getSqlUpgrades(data: UpgradesItem): String {
            return "INSERT OR ABORT INTO `cb_upgrades_table` " +
                    "(`cost`,`mod`,`purchased`,`description`,`action`,`label`,`id`,`prereq`) " +
                    "VALUES (${data.cost},${data.mod},${data.purchased!!.toInt()},'${data.description}','${data.action}','${data.label}','${data.id}','${data.prereq}')"
        }

    }

}

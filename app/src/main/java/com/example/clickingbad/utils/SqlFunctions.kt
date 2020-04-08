package com.example.clickingbad.utils

import com.example.clickingbad.business_logic.models.*
import java.util.*

object SqlFunctions {

    fun Boolean.toInt() = if (this) 1 else 0

    fun getSqlManufacturing(data: ManufacturingItem): String {
        return "INSERT OR ABORT INTO `cb_manufacturing_table` " +
                "(`label`,`description`,`amount`,`risk`,`rps`,`baseCost`,`cost`,`unlockRps`,`unlocked`,`id`) " +
                "VALUES ('${data.label}','${data.description}',${data.amount},${data.risk},${data.rps},${data.baseCost},${data.cost},${data.unlockRps},${data.unlocked!!.toInt()},'${data.id}')"
    }

    fun getSqlDistribution(data: DistributionItem): String {
        return "INSERT OR ABORT INTO `cb_distribution_table` " +
                "(`label`,`description`,`amount`,`risk`,`rps`,`baseCost`,`cost`,`unlockRps`,`unlocked`,`id`) " +
                "VALUES ('${data.label}','${data.description}',${data.amount},${data.risk},${data.rps},${data.baseCost},${data.cost},${data.unlockRps},${data.unlocked!!.toInt()},'${data.id}')"
    }

    fun getSqlLaundering(data: LaunderingItem): String {
        return "INSERT OR ABORT INTO `cb_laundering_table` " +
                "(`label`,`description`,`amount`,`rps`,`baseCost`,`cost`,`unlockRps`,`unlocked`,`id`) " +
                "VALUES ('${data.label}','${data.description}',${data.amount},${data.rps},${data.baseCost},${data.cost},${data.unlockRps},${data.unlocked!!.toInt()},'${data.id}')"
    }

    fun getSqlUpgrades(data: UpgradesItem): String {
        return "INSERT OR ABORT INTO `cb_upgrades_table` " +
                "(`label`,`description`,`action`,`unlocked`,`mod`,`cost`,`prereq`,`id`) " +
                "VALUES ('${data.label}','${data.description}','${data.action}',${data.unlocked!!.toInt()},${data.mod},${data.cost},'${data.prereq}','${data.id}')"
    }

    fun getSqlAchievements(data: AchievementsItem): String {
        return "INSERT OR ABORT INTO `cb_achievements_table` " +
                "(`label`,`description`,`property`,`required`,`unlocked`,`id`) " +
                "VALUES ('${data.label}','${data.description}','${data.property}',${data.required},${data.unlocked.toInt()},'${data.id}')"
    }

    fun getSqlEvents(data: EventsItem): String {
        return "INSERT OR ABORT INTO `cb_events_table` " +
                "(`name`,`description`,`chance`,`mod`,`type`,`id`) " +
                "VALUES ('${data.name}','${data.description}',${data.chance},${data.mod},'${data.type}','${data.id}')"
    }

    fun getSqlPlayerData(): String {
        return "INSERT OR ABORT INTO `cb_player_data_table` " +
                "(`id`,`batchAmount`,`batchRpsNet`,`batchRpsGross`,`batchPrice`,`cashAmount`,`cashLaundered`,`cashRps`,`lastTick`) " +
                "VALUES ('player',0,0,0,1,0,0,0,${Calendar.getInstance().timeInMillis})"
    }

    fun getSqlPlayerStats(): String {
        return "INSERT OR ABORT INTO `cb_player_stats_table` " +
                "(`id`,`batchesCooked`,`batchesCookedHand`,`batchesSold`,`batchesSoldHand`,`upgradesPurchased`,`cashEarned`,`cashSpent`,`secondsPlaying`) " +
                "VALUES ('player',0,0,0,0,0,0,0,0)"
    }

}

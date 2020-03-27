package com.example.clickingbad.utils

import com.example.clickingbad.business_logic.models.*

object SqlFunctions {

    fun Boolean.toInt() = if (this) 1 else 0

    fun getSqlManufacturing(data: ManufacturingItem): String {
        return "INSERT OR ABORT INTO `cb_manufacturing_table` " +
                "(`unlockRps`,`amount`,`cost`,`rps`,`baseCost`,`description`,`risk`,`label`,`id`,`unlocked`) " +
                "VALUES (${data.unlockRps},${data.amount},${data.cost},${data.rps},${data.baseCost},'${data.description}',${data.risk},'${data.label}','${data.id}',${data.unlocked!!.toInt()})"
    }

    fun getSqlDistribution(data: DistributionItem): String {
        return "INSERT OR ABORT INTO `cb_distribution_table` " +
                "(`unlockRps`,`amount`,`cost`,`rps`,`baseCost`,`description`,`risk`,`label`,`id`,`unlocked`) " +
                "VALUES (${data.unlockRps},${data.amount},${data.cost},${data.rps},${data.baseCost},'${data.description}',${data.risk},'${data.label}','${data.id}',${data.unlocked!!.toInt()})"
    }

    fun getSqlLaundering(data: LaunderingItem): String {
        return "INSERT OR ABORT INTO `cb_laundering_table` " +
                "(`unlockRps`,`amount`,`cost`,`rps`,`baseCost`,`description`,`label`,`id`,`unlocked`) " +
                "VALUES (${data.unlockRps},${data.amount},${data.cost},${data.rps},${data.baseCost},'${data.description}','${data.label}','${data.id}',${data.unlocked!!.toInt()})"
    }

    fun getSqlUpgrades(data: UpgradesItem): String {
        return "INSERT OR ABORT INTO `cb_upgrades_table` " +
                "(`cost`,`mod`,`purchased`,`description`,`action`,`label`,`id`,`prereq`) " +
                "VALUES (${data.cost},${data.mod},${data.purchased!!.toInt()},'${data.description}','${data.action}','${data.label}','${data.id}','${data.prereq}')"
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

    fun getSqlPlayerData(data: PlayerData): String {
        return "INSERT OR ABORT INTO `cb_player_data_table` " +
                "(`batch_amount`,`batch_rps_net`,`batch_rps_gross`,`batch_price`,`cash_amount`,`cash_laundered`,`cash_laundered_rps`) " +
                "VALUES (${data.batchAmount},${data.batchRpsNet},${data.batchRpsGross},${data.batchPrice},${data.cashAmount},${data.cashLaundered},${data.cashRps})"
    }

    fun getSqlPlayerStats(data: PlayerStats): String {
        return "INSERT OR ABORT INTO `cb_player_stats_table` " +
                "(`batches_cooked`,`batches_cooked_hand`,`batches_sold`,`batches_sold_hand`,`upgrades_purchased`,`cash_earned`,`cash_spent`,`seconds_playing`) " +
                "VALUES (${data.batchesCooked},${data.batchesCookedHand},${data.batchesSold},${data.batchesSoldHand},${data.upgradesPurchased},${data.cashEarned},${data.cashSpent},${data.secondsPlaying})"
    }

}

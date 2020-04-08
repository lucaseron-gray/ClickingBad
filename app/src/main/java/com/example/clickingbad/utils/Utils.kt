package com.example.clickingbad.utils

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.clickingbad.business_logic.models.GameLists
import com.google.gson.Gson
import java.nio.charset.Charset
import java.text.DecimalFormat
import kotlin.math.ln
import kotlin.math.pow

val numberSuffix =
    listOf("k", "M", "B", "T", "Q", "Qt", "Sx", "Sp", "O", "N", "D", "UD", "DD", "TD", "QD")

val purityMap =
    mapOf(
        1 to "Deadly",
        2 to "Dangerous",
        4 to "Unhealthy",
        6 to "Cloudy",
        10 to "Poor",
        13 to "Average",
        16 to "Good",
        20 to "Crystal",
        25 to "Blue Gold",
        50 to "Blue Platinum",
        100 to "FDA Approved",
        159 to "Atomically Perfect",
        211 to "Holy",
        300 to "Angelic",
        1000 to "Nectar of The Gods"
    )

// Purity string for fragment_game
fun formatPurity(price: Int): Spanned {
    return HtmlCompat.fromHtml(
        "Batches (\"${purityMap[price]}\")",
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )
}

// Format numbers to smaller ones, with suffix
fun withSuffix(number: Long): String {
    if (number < 1000.0) return "" + number
    val div: Int = (ln(number.toDouble()) / ln(1000.0)).toInt()

    val df = DecimalFormat("0.##")
    val newString = df.format(number / 1000.0.pow(div))
    return String.format("%s%s", newString, numberSuffix[div - 1])
}

// Format manufacturing, distribution and laundering values to HTML for styling
fun manufacturingValuesHtml(rps: Long, risk: Float): Spanned {
    return HtmlCompat.fromHtml(
        "Cooks <b>${formatRps(rps)}</b> per second; <b>${formatRisk(risk)}%</b> risk",
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )
}
fun distributionValuesHtml(rps: Long, risk: Float): Spanned {
    return HtmlCompat.fromHtml(
        "Sells <b>${formatRps(rps)}</b> per second; <b>${formatRisk(risk)}%</b> risk",
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )
}
fun launderingValuesHtml(rps: Long): Spanned {
    return HtmlCompat.fromHtml(
        "Launders <b>${formatRps(rps)}</b> per second",
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )
}

// Number formatting
fun formatCost(number: Long): String {
    return "$" + withSuffix(number)
}
fun formatRps(number: Long): String {
    val df = DecimalFormat("0.#")
    if (number < 1000) return df.format(number)
    return withSuffix(number)
}
fun formatRisk(number: Float): String {
    val df = DecimalFormat("0.#")
    return df.format(number.times(100))
}

// JSON data to initiate database
fun Context.readJsonAsset(filename: String): String {
    val inputStream = assets.open(filename)
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    return String(buffer, Charset.defaultCharset())
}
fun fetchJson(context: Context?): GameLists {
    val gson = Gson()
    val inputString = context?.readJsonAsset("database.json")

    return gson.fromJson(inputString, GameLists::class.java)
}

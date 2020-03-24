package com.example.clickingbad.utils

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.clickingbad.models.GameStore
import com.google.gson.Gson
import java.nio.charset.Charset
import java.text.DecimalFormat
import kotlin.math.ln
import kotlin.math.pow

val numberSuffix = listOf("k", "M", "B", "T", "Q", "Qt", "Sx", "Sp", "O", "N", "D", "UD", "DD", "TD", "QD")

// Format numbers to smaller ones, with suffix
fun withSuffix(number: Long) : String {
    if (number < 1000.0) return "" + number
    val div : Int = (ln(number.toDouble())/ln(1000.0)).toInt()

    val df = DecimalFormat("0.##")
    val newString = df.format(number/1000.0.pow(div))
    return String.format("%s%s", newString, numberSuffix[div-1])
}

// Format to HTML for styling
fun formatToHtml(rps: Double, risk: Float) : Spanned {
    return HtmlCompat.fromHtml("Cooks <b>${formatRps(rps)}</b> per second; <b>${formatRisk(risk)}%</b> risk", HtmlCompat.FROM_HTML_MODE_LEGACY)
}
// Format upgrade.cost
fun formatCost(number: Long) : String {
    return "$" + withSuffix(number)
}
// Format upgrade.rate
fun formatRps(number: Double) : String {
    val df = DecimalFormat("0.#")
    if (number < 1000.0) return df.format(number)
    return withSuffix(number.toLong())
}
// Format upgrade.risk
fun formatRisk(number: Float) : String {
    val df = DecimalFormat("0.#")
    return df.format(number.times(100))
}

// JSON data
fun Context.readJsonAsset(filename: String): String {
    val inputStream = assets.open(filename)
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    return String(buffer, Charset.defaultCharset())
}

fun fetchJson(context: Context?): GameStore {
    val gson = Gson()
    val inputString = context?.readJsonAsset("database.json")

    return gson.fromJson(inputString, GameStore::class.java)
}

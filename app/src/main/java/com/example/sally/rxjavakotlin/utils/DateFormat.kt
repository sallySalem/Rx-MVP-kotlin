package com.example.sally.rxjavakotlin.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Sally Salem on 2/4/18.
 */
fun String?.formatDateTime(): String {
    if (this == null) return "null"
    var currentDate = ""
    val sdfResponse = SimpleDateFormat(RESPONSE_DATA_TIME_FORMAT, Locale.ENGLISH)
    val sdfModel = SimpleDateFormat(MODEL_DATA_TIME_FORMAT, Locale.ENGLISH)

    try {
        val date = sdfResponse.parse(this)
        currentDate = sdfModel.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return currentDate
}
package com.example.rickandmortyapitest.common.extension

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import java.text.ParseException
import java.util.*

@SuppressLint("SimpleDateFormat", "NewApi")
fun toFormatDate(oldStringDate: String?): String? {
    if (oldStringDate == null || oldStringDate == "")
        return ""
    val newDate: String?
    val dateFormat = SimpleDateFormat("MMMM d yyyy - mm:ss", Locale.ENGLISH)
    newDate = try {
        val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(oldStringDate)
        dateFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        oldStringDate
    }
    return newDate
}
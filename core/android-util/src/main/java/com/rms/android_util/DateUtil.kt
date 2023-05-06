package com.rms.android_util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


fun LocalDate.toDateString(dateFormat: DateFormat = DateFormat.DD_MMM_YY): String {
    val formatter = DateTimeFormatter.ofPattern(dateFormat.format)
    return format(formatter)
}

fun String.toLocalDate(dateFormat: DateFormat) : LocalDate{
    val formatter = DateTimeFormatter.ofPattern(dateFormat.format)
    return LocalDate.parse(this, formatter)
}

fun getCurrentLocalDate(): LocalDate = LocalDate.now()

enum class DateFormat(val format: String) {
    DD_MM_YY("dd MM yy"),
    DD_MMM_YY("dd MMM yy")
}
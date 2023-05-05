package com.rms.android_util

import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun LocalDate.toDateString(dateFormat: DateFormat = DateFormat.DD_MMM_YY): String {
    val formatter = DateTimeFormatter.ofPattern(dateFormat.format)
    return format(formatter)
}


enum class DateFormat(val format: String) {
    DD_MM_YY("dd MM YY"),
    DD_MMM_YY("dd MMM YY")
}
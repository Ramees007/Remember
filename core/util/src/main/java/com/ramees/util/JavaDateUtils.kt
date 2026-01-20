import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toDateString(dateFormat: DateFormat = DateFormat.DD_MMM_YY): String {
    val formatter = DateTimeFormatter.ofPattern(dateFormat.format)
    return format(formatter)
}

fun String.toJavaLocalDate(dateFormat: DateFormat = DateFormat.DD_MMM_YY): LocalDate {
    val formatter = DateTimeFormatter.ofPattern(dateFormat.format)
    return LocalDate.parse(this, formatter)
}

fun getCurrentJavaLocalDate(): LocalDate = LocalDate.now()

fun LocalDate.toKmp(): kotlinx.datetime.LocalDate =
    kotlinx.datetime.LocalDate(year, monthValue, dayOfMonth)

fun kotlinx.datetime.LocalDate.toJava(): LocalDate =
    LocalDate.of(year, monthNumber, dayOfMonth)
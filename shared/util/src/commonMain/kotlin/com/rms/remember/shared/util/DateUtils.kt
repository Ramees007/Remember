import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun LocalDate.toDateString(
    format: DateFormat = DateFormat.DD_MMM_YY
): String = format.format().format(this)

fun String.toLocalDate(
    format: DateFormat = DateFormat.DD_MMM_YY
): LocalDate = format.format().parse(this)

@OptIn(ExperimentalTime::class)
fun getCurrentLocalDate(): LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

enum class DateFormat(val format: String) {
    DD_MM_YY("dd MM yy") {
        override fun format(): DateTimeFormat<LocalDate> {
            return LocalDate.Format {
                day()
                char(' ')
                monthNumber()
                char(' ')
                year()
            }
        }

    },
    DD_MMM_YY("dd MMM yy") {
        override fun format(): DateTimeFormat<LocalDate> {
            return LocalDate.Format {
                day()
                char(' ')
                monthName(MonthNames.ENGLISH_ABBREVIATED)
                char(' ')
                yearTwoDigits(1960)
            }
        }
    };

    abstract fun format(): DateTimeFormat<LocalDate>
}
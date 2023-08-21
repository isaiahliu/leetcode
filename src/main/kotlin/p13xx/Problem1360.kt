package p13xx

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue
import util.expect

fun main() {
    class Solution {
        fun daysBetweenDates(date1: String, date2: String): Int {
            return LocalDate.parse(date1, DateTimeFormatter.ISO_DATE)
                .until(LocalDate.parse(date2, DateTimeFormatter.ISO_DATE), ChronoUnit.DAYS).toInt().absoluteValue
        }
    }

    expect {
        Solution().daysBetweenDates(
            "2019-06-29", "2020-06-29"
        )
    }
}


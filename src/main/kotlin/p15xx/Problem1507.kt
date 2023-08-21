package p15xx

import util.expect

fun main() {
    class Solution {
        fun reformatDate(date: String): String {
            val months = mapOf(
                "Jan" to "01",
                "Feb" to "02",
                "Mar" to "03",
                "Apr" to "04",
                "May" to "05",
                "Jun" to "06",
                "Jul" to "07",
                "Aug" to "08",
                "Sep" to "09",
                "Oct" to "10",
                "Nov" to "11",
                "Dec" to "12"
            )
            return "(\\d+)\\w+ (\\w+) (\\d+)".toRegex().matchEntire(date)?.groupValues?.let { (_, day, month, year) ->
                "${year}-${months[month]}-${day.padStart(2, '0')}"
            }.orEmpty()
        }
    }

    expect {
        Solution().reformatDate(
            "20th Oct 2052",
        )
    }
}


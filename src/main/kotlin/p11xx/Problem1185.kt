package p11xx

import util.expect
import java.time.LocalDate

fun main() {
    class Solution {
        fun dayOfTheWeek(day: Int, month: Int, year: Int): String {
            val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

            return days[LocalDate.of(year, month, day).dayOfWeek.ordinal]
        }
    }

    expect {
        Solution().dayOfTheWeek(
            1, 2, 3
        )
    }
}


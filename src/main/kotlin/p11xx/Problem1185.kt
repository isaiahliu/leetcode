package p11xx

import java.time.LocalDate
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun dayOfTheWeek(day: Int, month: Int, year: Int): String {
            val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

            return days[LocalDate.of(year, month, day).dayOfWeek.ordinal]
        }
    }

    measureTimeMillis {
        println(
            Solution().dayOfTheWeek(
                1, 2, 3
            )
        )
    }.also { println("Time cost: ${it}ms") }
}


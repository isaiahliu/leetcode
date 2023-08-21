package p11xx

import util.expect

fun main() {
    class Solution {
        fun dayOfYear(date: String): Int {
            val monthDays = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

            return "(\\d{4})-(\\d{2})-(\\d{2})".toRegex().matchEntire(date)?.groupValues?.drop(1)?.map { it.toInt() }
                ?.let { (year, month, day) ->
                    if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                        monthDays[1]++
                    }

                    (0 until month - 1).map { monthDays[it] }.sum() + day
                } ?: 0
        }
    }

    expect {
        Solution().dayOfYear(
            ""
        )
    }
}
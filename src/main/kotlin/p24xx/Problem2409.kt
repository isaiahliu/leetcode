package p24xx

import util.expect

fun main() {
    class Solution {
        fun countDaysTogether(arriveAlice: String, leaveAlice: String, arriveBob: String, leaveBob: String): Int {
            fun String.toDate(): Pair<Int, Int> {
                return split("-").let { it[0].toInt() to it[1].toInt() }
            }

            val (arriveMonth, arriveDate) = arriveAlice.coerceAtLeast(arriveBob).toDate()
            val (leaveMonth, leaveDate) = leaveAlice.coerceAtMost(leaveBob).toDate()

            return when {
                arriveMonth > leaveMonth -> 0
                arriveMonth == leaveMonth -> (leaveDate - arriveDate + 1).coerceAtLeast(0)
                else -> {
                    var result = leaveDate

                    for (month in arriveMonth until leaveMonth) {
                        result += when (month) {
                            1, 3, 5, 7, 8, 10, 12 -> 31
                            2 -> 28
                            else -> 30
                        }

                        if (month == arriveMonth) {
                            result -= (arriveDate - 1)
                        }
                    }
                    result
                }
            }
        }
    }

    expect {
        Solution().countDaysTogether(
            "08-15", "08-18", "08-16", "08-19"
        )
    }
}
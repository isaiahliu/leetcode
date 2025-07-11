package p31xx

import util.expect

fun main() {
    class Solution {
        fun countDays(days: Int, meetings: Array<IntArray>): Int {
            meetings.sortBy { it[0] }

            var result = 0

            var last = 0
            meetings.forEach { (start, end) ->
                result += maxOf(start - last - 1, 0)

                last = maxOf(last, end)
            }

            result += maxOf(days - last, 0)
            return result
        }
    }

    expect {
        Solution().countDays(
            1, arrayOf()
        )
    }
}

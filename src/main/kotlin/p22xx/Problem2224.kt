package p22xx

import util.expect

fun main() {
    class Solution {
        fun convertTime(current: String, correct: String): Int {
            var (h, m) = current.split(":").map { it.toInt() }
            val (th, tm) = correct.split(":").map { it.toInt() }

            var result = 0

            while (h + 1 < th) {
                h++
                result++
            }

            if (h < th) {
                if (m <= tm) {
                    result++
                } else {
                    m -= 60
                }
            }

            while (m + 15 <= tm) {
                m += 15
                result++
            }

            while (m + 5 <= tm) {
                m += 5
                result++
            }

            result += tm - m

            return result
        }
    }

    expect {
        Solution().convertTime(
            "hhzjhhzajsoiz", ""
        )
    }
}
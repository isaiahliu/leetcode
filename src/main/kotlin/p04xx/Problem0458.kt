package p04xx

import util.expect

fun main() {
    class Solution {
        fun poorPigs(buckets: Int, minutesToDie: Int, minutesToTest: Int): Int {
            val base = minutesToTest / minutesToDie + 1

            var t = 1
            var pigCount = 0
            while (t < buckets) {
                t *= base
                pigCount++
            }
            return pigCount
        }
    }

    expect {
        Solution().poorPigs(1000, 15, 60)
    }
}
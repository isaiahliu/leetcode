package p18xx

import util.expect

fun main() {
    class Solution {
        fun sumOfFlooredPairs(nums: IntArray): Int {
            val m = 1000000007
            val counts = IntArray(100001)

            var min = Int.MAX_VALUE
            var max = Int.MIN_VALUE
            var sumForOne = 0L

            nums.forEach {
                min = min.coerceAtMost(it)
                max = max.coerceAtLeast(it)

                counts[it]++
                sumForOne += it
            }

            for (i in min..max) {
                counts[i] += counts[i - 1]
            }

            var result = 0L
            nums.forEach {
                if (it == 1) {
                    result += sumForOne
                } else {
                    var times = 1
                    var from = it
                    while (from <= max) {
                        result += (counts[(from + it - 1).coerceAtMost(max)] - counts[from - 1]) * (times++)
                        result %= m

                        from += it
                    }
                }
            }

            return result.toInt()
        }
    }

    expect {
        Solution().sumOfFlooredPairs(intArrayOf(1, 2, 3, 4, 5))
    }
}


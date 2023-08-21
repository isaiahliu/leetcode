package p15xx

import util.expect

fun main() {
    class Solution {
        fun numOfSubarrays(arr: IntArray): Int {
            val m = 1000000007
            val counts = intArrayOf(0, 0)
            var oddPos = 0

            var result = 0L

            arr.forEach {
                if (it % 2 == 0) {
                    counts[1 - oddPos]++
                } else {
                    oddPos = 1 - oddPos
                    counts[oddPos]++
                }

                result += counts[oddPos]

                result %= m
            }

            return result.toInt()
        }
    }

    expect {
        Solution().numOfSubarrays(
            intArrayOf()
        )
    }
}


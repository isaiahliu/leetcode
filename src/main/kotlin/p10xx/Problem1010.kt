package p10xx

import util.expect

fun main() {
    class Solution {
        fun numPairsDivisibleBy60(time: IntArray): Int {
            val map = hashMapOf<Int, Int>()

            var result = 0
            time.forEach {
                (it % 60).also {
                    map[(60 - it) % 60]?.also { result += it }
                    map[it] = (map[it] ?: 0) + 1
                }
            }

            return result
        }
    }

    expect {
        Solution().numPairsDivisibleBy60(
            intArrayOf(30, 20, 150, 100, 40)
        )
    }
}

package p22xx

import util.expect

fun main() {
    class Solution {
        fun minimumRounds(tasks: IntArray): Int {
            var result = 0
            tasks.toList().groupingBy { it }.eachCount().values.forEach {
                when {
                    it == 1 -> {
                        return -1
                    }

                    it % 3 > 0 -> {
                        result++
                    }
                }
                result += it / 3
            }

            return result
        }
    }

    expect {
        Solution().minimumRounds(
            intArrayOf()
        )
    }
}
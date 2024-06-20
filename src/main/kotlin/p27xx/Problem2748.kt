package p27xx

import util.expect

fun main() {
    class Solution {
        fun countBeautifulPairs(nums: IntArray): Int {
            val pairs = arrayOf(
                setOf(1),
                setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
                setOf(1, 3, 5, 7, 9),
                setOf(1, 2, 4, 5, 7, 8),
                setOf(1, 3, 5, 7, 9),
                setOf(1, 2, 3, 4, 6, 7, 8, 9),
                setOf(1, 5, 7),
                setOf(1, 2, 3, 4, 5, 6, 8, 9),
                setOf(1, 3, 5, 7, 9),
                setOf(1, 2, 4, 5, 7, 8),
            )

            val prefix = IntArray(10)
            var result = 0
            nums.forEach {
                val p = it.toString().first() - '0'
                val s = it % 10

                result += pairs[s].sumOf { prefix[it] }

                prefix[p]++
            }

            return result
        }
    }

    expect {
        Solution().countBeautifulPairs(
            intArrayOf()
        )
    }
}

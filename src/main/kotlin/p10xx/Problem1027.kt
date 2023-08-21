package p10xx

import util.expect

fun main() {
    class Solution {
        fun longestArithSeqLength(nums: IntArray): Int {
            val map = hashMapOf<Int, MutableMap<Int, Int>>()

            var result = 1

            nums.forEach { num ->
                val numMap = map[num].orEmpty().toMutableMap()

                map.forEach { key, m ->
                    val diff = num - key
                    val length = (m[diff] ?: 1) + 1

                    result = result.coerceAtLeast(length)

                    numMap[diff] = length
                }

                map[num] = numMap
            }

            return result
        }
    }

    expect {
        Solution().longestArithSeqLength(
            intArrayOf(3, 6, 4, 9)
        )
    }
}

package p07xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
            if (k <= 1) {
                return 0
            }

            val map = TreeSet<IntArray>(compareBy { it[0] })

            var result = 0
            nums.forEach { num ->
                if (num >= k) {
                    map.clear()
                } else {
                    while (map.lastOrNull()?.takeIf { it[0] * num >= k } != null) {
                        map.pollLast()
                    }
                    result++
                    map.forEach {
                        it[0] *= num

                        result += it[1]
                    }

                    map.lower(intArrayOf(num + 1))?.takeIf { it[0] == num }?.also {
                        it[1]++
                    } ?: run {
                        map.add(intArrayOf(num, 1))
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().numSubarrayProductLessThanK(intArrayOf(100, 2, 3, 4), 100)
    }
}
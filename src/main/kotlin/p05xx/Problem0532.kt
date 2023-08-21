package p05xx

import kotlin.math.sign
import util.expect

fun main() {
    class Solution {
        fun findPairs(nums: IntArray, k: Int): Int {
            if (k == 0) {
                return nums.toList().groupingBy { it }.eachCount().values.map { (it - 1).sign }.sum()
            }

            val set = hashSetOf<Int>()

            var result = 0
            nums.sorted().forEach { num ->
                if (set.add(num)) {
                    if (num - k in set) {
                        result++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().findPairs(intArrayOf(1, 1, 1, 2, 2, 2), 0)
    }
}
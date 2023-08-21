package p09xx

import util.expect

fun main() {
    class Solution {
        fun repeatedNTimes(nums: IntArray): Int {
            val visited = hashSetOf<Int>()
            nums.forEach {
                if (!visited.add(it)) {
                    return it
                }
            }
            return 0
        }
    }

    expect {
        Solution().repeatedNTimes(
            intArrayOf(1)
        )
    }
}

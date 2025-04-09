package p33xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(nums: IntArray, k: Int): Int {
            val set = hashSetOf<Int>()

            nums.forEach {
                when {
                    it < k -> return -1
                    it > k -> set.add(it)
                }
            }

            return set.size
        }
    }

    expect {
        Solution().minOperations(
            intArrayOf(), 0
        )
    }
}

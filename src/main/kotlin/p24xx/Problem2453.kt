package p24xx

import util.expect

fun main() {
    class Solution {
        fun destroyTargets(nums: IntArray, space: Int): Int {
            return nums.groupBy { it % space }.values.map {
                it.min() to it.size
            }.maxWith(compareBy<Pair<Int, Int>> { it.second }.thenByDescending { it.first }).first
        }
    }

    expect {
        Solution().destroyTargets(
            intArrayOf(), 1
        )
    }
}
package p26xx

import util.expect

fun main() {
    class Solution {
        fun maxDivScore(nums: IntArray, divisors: IntArray): Int {
            return divisors.maxWith(compareBy<Int> { d -> nums.count { it % d == 0 } }.thenByDescending { it })
        }
    }

    expect {
        Solution().maxDivScore(intArrayOf(), intArrayOf())
    }
}
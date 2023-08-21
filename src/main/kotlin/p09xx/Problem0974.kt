package p09xx

import util.expect

fun main() {
    class Solution {
        fun subarraysDivByK(nums: IntArray, k: Int): Int {
            val summods = IntArray(k)
            summods[0] = 1

            fun Int.m(mod: Int): Int {
                return ((this % mod) + mod) % mod
            }

            var result = 0
            var sum = 0
            nums.forEach {
                sum = (sum + it).m(k)
                result += summods[sum]
                summods[sum]++
            }

            return result
        }
    }

    expect {
        Solution().subarraysDivByK(
            intArrayOf(2, 4, 1), 5
        )
    }
}

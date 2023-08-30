package p21xx

import util.expect

fun main() {
    class Solution {
        fun countPairs(nums: IntArray, k: Int): Int {
            var result = 0
            nums.indices.groupBy { nums[it] }.values.forEach {
                for (i in it.indices) {
                    for (j in i + 1 until it.size) {
                        if (it[i] * it[j] % k == 0) {
                            result++
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().countPairs(
            intArrayOf(1), 9
        )
    }
}
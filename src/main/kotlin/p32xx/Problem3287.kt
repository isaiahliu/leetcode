package p32xx

import util.expect

fun main() {
    class Solution {
        fun maxValue(nums: IntArray, k: Int): Int {
            fun IntArray.findORs(): List<Set<Int>> {
                val dp = arrayListOf<MutableSet<Int>>()
                val prev = List(k + 1) { hashSetOf<Int>() }
                prev[0] += 0
                for (i in 0 until size) {
                    for (j in minOf(k - 1, i + 1) downTo 0) {
                        for (x in prev[j]) {
                            prev[j + 1] += x or this[i]
                        }
                    }
                    dp.add(prev[k].toMutableSet())
                }
                return dp
            }

            val forward = nums.findORs()
            val backward = nums.reversedArray().findORs()
            var result = 0
            for (i in k - 1 until nums.size - k) {
                for (a in forward[i]) {
                    for (b in backward[nums.size - i - 2]) {
                        result = maxOf(result, a xor b)
                    }
                }
            }
            return result
        }
    }

    expect {
        Solution().maxValue(
            intArrayOf(2, 6, 7), 1
        )
    }
}

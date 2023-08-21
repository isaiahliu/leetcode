package p15xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(nums: IntArray): Int {
            var result = 0
            val binaries = nums.map { it.toString(2).padStart(32, '0').reversed() }

            var move = 0
            repeat(32) { pos ->
                var count = 0
                binaries.forEach {
                    if (it[pos] == '1') {
                        count++
                    }
                }

                if (count > 0) {
                    result += count + move
                    move = 0
                }
                move++
            }

            return result
        }
    }

    expect {
        Solution().minOperations(
            intArrayOf(1, 5)
        )
    }
}


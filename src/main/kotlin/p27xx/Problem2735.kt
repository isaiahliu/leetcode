package p27xx

import util.expect

fun main() {
    class Solution {
        fun minCost(nums: IntArray, x: Int): Long {
            var temp = nums
            var result = temp.fold(0L) { a, b -> a + b }

            var baseCost = 0L
            repeat(nums.size) {
                baseCost += x

                var sum = baseCost
                temp = IntArray(temp.size) {
                    minOf(temp[it], temp[(it + 1).mod(temp.size)]).also { sum += it }
                }

                result = minOf(result, sum)
            }

            return result
        }
    }

    expect(119) {
        Solution().minCost(
            intArrayOf(31, 25, 18, 59), 27
        )
    }

    expect(298) {
        Solution().minCost(
            intArrayOf(15, 150, 56, 69, 214, 203), 42
        )
    }
}

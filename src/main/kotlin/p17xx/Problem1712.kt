package p17xx

import util.expect

fun main() {
    class Solution {
        fun waysToSplit(nums: IntArray): Int {
            var sum = 0
            val sums = IntArray(nums.size) {
                sum += nums[it]
                sum
            }

            var result = 0L
            val m = 1000000007

            fun binarySearchLeft(leftSum: Int, start: Int, end: Int): Int? {
                if (start > end) {
                    return null
                }

                val mid = (start + end) / 2
                val midSum = sums[mid] - leftSum

                return if (midSum >= leftSum) {
                    binarySearchLeft(leftSum, start, mid - 1) ?: mid
                } else {
                    binarySearchLeft(leftSum, mid + 1, end)
                }
            }

            fun binarySearchRight(leftSum: Int, start: Int, end: Int): Int? {
                if (start > end) {
                    return null
                }

                val mid = (start + end) / 2
                val midSum = sums[mid] - leftSum
                val rightSum = sum - sums[mid]

                return if (rightSum >= midSum) {
                    binarySearchRight(leftSum, mid + 1, end) ?: mid
                } else {
                    binarySearchRight(leftSum, start, mid - 1)
                }
            }

            for (leftEnd in 0 until nums.size - 1) {
                val leftSum = sums[leftEnd]

                val midMin = binarySearchLeft(leftSum, leftEnd + 1, nums.lastIndex - 1) ?: break
                val midMax = binarySearchRight(leftSum, midMin, nums.lastIndex - 1) ?: continue

                (midMax - midMin + 1).takeIf { it > 0 }?.also {
                    result += it
                    result %= m
                } ?: break
            }

            return result.toInt()
        }
    }

    expect {
        Solution().waysToSplit(
            intArrayOf(
                8892,
                2631,
                7212,
                1188,
                6580,
                1690,
                5950,
                7425,
                8787,
                4361,
                9849,
                4063,
                9496,
                9140,
                9986,
                1058,
                2734,
                6961,
                8855,
                2567,
                7683,
                4770,
                40,
                850,
                72,
                2285,
                9328,
                6794,
                8632,
                9163,
                3928,
                6962,
                6545,
                6920,
                926,
                8885,
                1570,
                4454,
                6876,
                7447,
                8264,
                3123,
                2980,
                7276,
                470,
                8736,
                3153,
                3924,
                3129,
                7136,
                1739,
                1354,
                661,
                1309,
                6231,
                9890,
                58,
                4623,
                3555,
                3100,
                3437
            )
        )
    }
}

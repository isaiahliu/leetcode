package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSumTwoNoOverlap(nums: IntArray, firstLen: Int, secondLen: Int): Int {
            var sum = 0
            val preSum = IntArray(nums.size) {
                sum += nums[it]
                sum
            }

            fun buildArray(len: Int): Array<IntArray> {
                val result = Array(nums.size) { intArrayOf(-1, -1) }

                for (i in len - 1 until nums.size) {
                    result[i][0] = (result.getOrNull(i - 1)?.getOrNull(0)
                        ?: -1).coerceAtLeast(preSum[i] - (preSum.getOrNull(i - len) ?: 0))

                    result[nums.lastIndex - i][1] = (result.getOrNull(nums.lastIndex - i + 1)?.getOrNull(1)
                        ?: -1).coerceAtLeast(
                        preSum[nums.lastIndex - i + len - 1] - (preSum.getOrNull(nums.lastIndex - i - 1) ?: 0)
                    )
                }

                return result
            }

            val array1 = buildArray(firstLen)

            val array2 = if (firstLen == secondLen) {
                array1
            } else {
                buildArray(secondLen)
            }

            var max = -1
            for (i in 0 until nums.lastIndex) {
                max = max.coerceAtLeast(array1[i][0] + array2[i + 1][1])
                max = max.coerceAtLeast(array2[i][0] + array1[i + 1][1])
            }

            return max
        }
    }

    measureTimeMillis {
        Solution().maxSumTwoNoOverlap(
            intArrayOf(2, 1, 5, 6, 0, 9, 5, 0, 3, 8), 4, 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
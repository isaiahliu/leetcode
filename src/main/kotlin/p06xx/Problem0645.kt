package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findErrorNums(nums: IntArray): IntArray {
            var index = 0

            var errorNum = 0
            var errorPos = 0
            while (index < nums.size) {
                if (index + 1 == nums[index]) {
                    index++
                } else {
                    val t = nums[index]

                    if (t == nums[t - 1]) {
                        errorNum = t

                        if (index + 1 != errorNum) {
                            errorPos = index + 1
                        }

                        index++
                        continue
                    }
                    nums[index] = nums[t - 1]
                    nums[t - 1] = t
                }
            }

            return intArrayOf(errorNum, errorPos)
        }
    }

    measureTimeMillis {
        Solution().findErrorNums(
            intArrayOf(8, 8, 2, 5, 3, 6, 1, 4)
        ).toList().also { println(it) }
        Solution().findErrorNums(
            intArrayOf(8, 7, 3, 5, 3, 6, 1, 4)
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
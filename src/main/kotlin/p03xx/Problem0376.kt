package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun wiggleMaxLength(nums: IntArray): Int {
            var length = 1

            var up = false

            for (i in 1 until nums.size) {
                when {
                    nums[i] > nums[i - 1] -> {
                        if (length == 1 || !up) {
                            up = true
                            length++
                        }
                    }

                    nums[i] < nums[i - 1] -> {
                        if (length == 1 || up) {
                            up = false
                            length++
                        }
                    }
                }
            }

            return length
        }
    }

    measureTimeMillis {
        Solution().wiggleMaxLength(
            intArrayOf(1, 7, 4, 9, 2, 5)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


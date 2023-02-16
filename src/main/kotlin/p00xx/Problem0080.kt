package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun removeDuplicates(nums: IntArray): Int {
            var previousNum = nums[0]
            var previousNumCount = 1
            var writeIndex = 1

            for (i in 1 until nums.size) {
                val num = nums[i]

                if (num == previousNum) {
                    previousNumCount++
                } else {
                    previousNum = num
                    previousNumCount = 1
                }

                if (previousNumCount <= 2) {
                    nums[writeIndex++] = num
                }
            }

            return writeIndex
        }
    }

    measureTimeMillis {
        println(Solution().removeDuplicates(intArrayOf()))
    }.also { println("Time cost: ${it}ms") }
}


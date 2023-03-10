package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findDuplicates(nums: IntArray): List<Int> {
            val result = arrayListOf<Int>()

            var index = 0

            while (index < nums.size) {
                val num = nums[index]
                val targetIndex = num - 1

                when {
                    targetIndex < 0 -> {
                        index++
                    }

                    index == targetIndex -> {
                        index++
                    }

                    nums[targetIndex] == num -> {
                        result.add(num)
                        nums[index++] = 0
                    }

                    else -> {
                        nums[index] = nums[targetIndex]
                        nums[targetIndex] = num
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findDuplicates(
            intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}



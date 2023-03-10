package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findDisappearedNumbers(nums: IntArray): List<Int> {
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

                    nums[index] == nums[targetIndex] -> {
                        nums[index++] = 0
                    }

                    else -> {
                        nums[index] = nums[targetIndex]
                        nums[targetIndex] = num
                    }
                }
            }

            val result = arrayListOf<Int>()

            nums.forEachIndexed { i, num ->
                if (num == 0) {
                    result.add(i + 1)
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findDisappearedNumbers(
            intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
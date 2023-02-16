package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sortColors(nums: IntArray): Unit {
            var leftIndex = 0
            var rightIndex = nums.size - 1

            var cursor = 0
            while (cursor <= rightIndex) {
                when (nums[cursor]) {
                    0 -> {
                        val t = nums[leftIndex]
                        nums[cursor] = t
                        nums[leftIndex] = 0

                        leftIndex++

                        if (cursor < leftIndex) {
                            cursor = leftIndex
                        }
                    }

                    2 -> {
                        val t = nums[rightIndex]
                        nums[cursor] = t
                        nums[rightIndex] = 2

                        rightIndex--
                    }

                    else -> {
                        cursor++
                    }
                }
            }
        }
    }

    measureTimeMillis {
        println(
            Solution().sortColors(intArrayOf(0, 2, 1))
        )
    }.also { println("Time cost: ${it}ms") }
}


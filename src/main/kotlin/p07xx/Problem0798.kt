package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun bestRotation(nums: IntArray): Int {
            val map = hashMapOf<Int, Int>()
            var maxScore = 0
            nums.forEachIndexed { index, i ->
                if (index - i >= 0) {
                    maxScore++
                }
                map[index - i] = (map[index - i] ?: 0) + 1
            }

            var result = 0

            var score = maxScore
            repeat(nums.size - 1) { moveIndex ->
                score -= map[moveIndex] ?: 0

                map[moveIndex - nums[moveIndex]] = (map[moveIndex - nums[moveIndex]] ?: 0) - 1
                map[nums.size + moveIndex - nums[moveIndex]] = (map[nums.size + moveIndex - nums[moveIndex]] ?: 0) + 1
                score++

                if (score > maxScore) {
                    maxScore = score
                    result = moveIndex + 1
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().bestRotation(
            intArrayOf(2, 4, 1, 3, 0)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
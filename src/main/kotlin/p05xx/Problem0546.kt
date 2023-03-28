package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun removeBoxes(boxes: IntArray): Int {
            val nums = arrayListOf<Pair<Int, Int>>()

            var lastNum = boxes[0]
            var t = 0
            boxes.forEach {
                if (it != lastNum) {
                    nums.add(lastNum to t)
                    t = 0
                    lastNum = it
                }

                t++
            }

            nums.add(lastNum to t)

            val dp = Array(nums.size) {
                Array(nums.size) { hashMapOf<Int, Int>() }
            }

            fun calculate(left: Int, right: Int, remainingCount: Int): Int {
                if (left > right) {
                    return 0
                }

                return dp[left][right][remainingCount] ?: run {
                    val remainingWithRight = remainingCount + nums[right].second

                    var max = calculate(left, right - 1, 0) + remainingWithRight * remainingWithRight

                    for (i in left until right) {
                        if (nums[i].first == nums[right].first) {
                            max = max.coerceAtLeast(
                                calculate(left, i, remainingWithRight) + calculate(
                                    i + 1, right - 1, 0
                                )
                            )
                        }
                    }
                    dp[left][right][remainingCount] = max

                    max
                }
            }

            return calculate(0, dp.lastIndex, 0)
        }
    }

    measureTimeMillis {
        Solution().removeBoxes(
            intArrayOf(1, 3, 2, 2, 2, 3, 4, 3, 1)
        ).also { println(it) }
        Solution().removeBoxes(
            intArrayOf(
                1,
                2,
                2,
                1,
                1,
                1,
                2,
                1,
                1,
                2,
                1,
                2,
                1,
                1,
                2,
                2,
                1,
                1,
                2,
                2,
                1,
                1,
                1,
                2,
                2,
                2,
                2,
                1,
                2,
                1,
                1,
                2,
                2,
                1,
                2,
                1,
                2,
                2,
                2,
                2,
                2,
                1,
                2,
                1,
                2,
                2,
                1,
                1,
                1,
                2,
                2,
                1,
                2,
                1,
                2,
                2,
                1,
                2,
                1,
                1,
                1,
                2,
                2,
                2,
                2,
                2,
                1,
                2,
                2,
                2,
                2,
                2,
                1,
                1,
                1,
                1,
                1,
                2,
                2,
                2,
                2,
                2,
                1,
                1,
                1,
                1,
                2,
                2,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                2,
                1,
                2,
                2,
                1
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
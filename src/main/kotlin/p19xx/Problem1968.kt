package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun rearrangeArray(nums: IntArray): IntArray {
            nums.sort()

            return IntArray(nums.size) {
                if (it % 2 == 0) {
                    nums[it / 2]
                } else {
                    nums[nums.lastIndex - it / 2]
                }
            }
        }
    }

    measureTimeMillis {
        Solution().rearrangeArray(
            intArrayOf()
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
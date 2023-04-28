package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sortArrayByParityII(nums: IntArray): IntArray {
            fun moveIndex(i: Int): Int {
                var t = i
                while (t < nums.size && (t xor nums[t]) and 1 == 0) {
                    t += 2
                }
                return t
            }

            var index1 = moveIndex(0)
            var index2 = moveIndex(1)

            while (index1 < nums.size && index2 < nums.size) {
                val t = nums[index1]
                nums[index1] = nums[index2]
                nums[index2] = t

                index1 = moveIndex(index1)
                index2 = moveIndex(index2)
            }

            return nums
        }
    }

    measureTimeMillis {
        Solution().sortArrayByParityII(
            intArrayOf(4, 2, 5, 7)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
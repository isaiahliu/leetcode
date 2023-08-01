package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxNonOverlapping(nums: IntArray, target: Int): Int {
            var result = 0

            val set = hashSetOf(0)

            var sum = 0
            nums.forEach {
                sum += it
                if (sum - target in set) {
                    result++
                    set.clear()
                    set.add(0)
                    sum = 0
                } else {
                    set.add(sum)
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxNonOverlapping(
            intArrayOf(), 1
        ).also { println(it) }
    }
}


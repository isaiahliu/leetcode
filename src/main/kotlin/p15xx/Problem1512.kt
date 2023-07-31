package p15xx

import util.Ints
import util.get
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numIdenticalPairs(nums: IntArray): Int {
            return nums.toList().groupingBy { it }.eachCount().values.map {
                it * (it - 1) / 2
            }.sum()
        }
    }

    measureTimeMillis {
        Solution().numIdenticalPairs(
            Ints[1]
        ).also { println(it) }
    }
}


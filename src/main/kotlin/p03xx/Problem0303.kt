package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class NumArray(private val nums: IntArray) {
        init {
            for (i in 1 until nums.size) {
                nums[i] += nums[i - 1]
            }
        }

        fun sumRange(left: Int, right: Int): Int {
            return nums[right] - nums.getOrElse(left - 1) { 0 }
        }
    }

    measureTimeMillis {
        NumArray(intArrayOf()).also { println(it) }
    }
}


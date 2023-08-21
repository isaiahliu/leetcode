package p03xx

import util.expect

fun main() {
    class NumArray(private val nums: IntArray) {
        val sumCache = IntArray(nums.size) { Int.MIN_VALUE }

        private fun sumBefore(index: Int): Int {
            if (index < 0) {
                return 0
            }

            if (sumCache[index] > Int.MIN_VALUE) {
                return sumCache[index]
            }

            var result = nums[index]
            if (index > 0) {
                result += sumBefore(index - 1)
            }

            sumCache[index] = result

            return result
        }

        fun sumRange(left: Int, right: Int): Int {
            return sumBefore(right) - sumBefore(left - 1)
        }
    }

    expect {
        NumArray(intArrayOf())
    }
}


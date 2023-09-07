package p23xx

import util.expect

fun main() {
    class Solution {
        fun maximumSum(nums: IntArray): Int {
            return nums.groupBy {
                var sum = 0
                var t = it
                while (t > 0) {
                    sum += t % 10
                    t /= 10
                }
                sum
            }.values.filter { it.size > 1 }.maxOfOrNull {
                var max1 = 0
                var max2 = 0
                it.forEach {
                    when {
                        it > max1 -> {
                            max2 = max1
                            max1 = it
                        }

                        it > max2 -> {
                            max2 = it
                        }
                    }
                }

                max1 + max2
            } ?: -1
        }
    }

    expect {
        Solution().maximumSum(
            intArrayOf(18, 43, 36, 13, 7)
        )
    }
}
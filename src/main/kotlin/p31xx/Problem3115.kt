package p31xx

import util.expect

fun main() {
    class Solution {
        fun maximumPrimeDifference(nums: IntArray): Int {
            var min = Int.MAX_VALUE
            var max = -1

            val cache = hashMapOf(1 to false)

            fun Int.isPrime(): Boolean {
                return cache.computeIfAbsent(this) {
                    for (i in 2..this / 2) {
                        if (this % i == 0) {
                            return@computeIfAbsent false
                        }
                    }
                    return@computeIfAbsent true
                }
            }

            nums.forEachIndexed { index, n ->
                if (n.isPrime()) {
                    min = minOf(min, index)
                    max = maxOf(max, index)
                }
            }

            return max - min
        }
    }

    expect {
        Solution().maximumPrimeDifference(
            intArrayOf(4, 2, 9, 5, 3)
        )
    }
}

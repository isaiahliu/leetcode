package p31xx

import util.expect

fun main() {
    class Solution {
        fun maximumLength(nums: IntArray, k: Int): Int {
            val dp = hashMapOf<Int, IntArray>()
            val zd = IntArray(k + 1)

            for (num in nums) {
                val current = dp.computeIfAbsent(num) { IntArray(k + 1) }
                for (j in 0..k) {
                    current[j] = current[j] + 1
                    if (j > 0) {
                        current[j] = maxOf(current[j], (zd[j - 1] + 1))
                    }
                }
                for (j in 0..k) {
                    zd[j] = maxOf(zd[j], current[j])
                }
            }
            return zd[k]
        }
    }

    expect {
        Solution().maximumLength(
            intArrayOf(
                2
            ), 1
        )
    }
}

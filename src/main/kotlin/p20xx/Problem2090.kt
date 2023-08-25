package p20xx

import util.expect

fun main() {
    class Solution {
        fun getAverages(nums: IntArray, k: Int): IntArray {
            var sum = 0L
            repeat(k.coerceAtMost(nums.size)) {
                sum += nums[it]
            }

            return IntArray(nums.size) { index ->
                nums.getOrNull(index + k)?.let {
                    sum += it

                    (sum / (k * 2 + 1)).takeIf {
                        nums.getOrNull(index - k)?.also { sum -= it } != null
                    }
                }?.toInt() ?: -1
            }
        }
    }

    expect {
        Solution().getAverages(
            intArrayOf(7, 4, 3, 9, 1, 8, 5, 2, 6), 3
        ).toList()
    }
}
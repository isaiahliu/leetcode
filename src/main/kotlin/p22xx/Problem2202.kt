package p22xx

import util.expect

fun main() {
    class Solution {
        fun maximumTop(nums: IntArray, k: Int): Int {
            return when {
                nums.size == 1 -> {
                    k.takeIf { it % 2 == 0 }?.let { nums[0] } ?: -1
                }

                k == 0 -> nums[0]
                k == 1 -> nums.getOrNull(1) ?: -1
                else -> {
                    var result = nums.take(k - 1).max()

                    nums.getOrNull(k)?.also {
                        result = result.coerceAtLeast(it)
                    }

                    result
                }
            }
        }
    }

    expect {
        Solution().maximumTop(
            intArrayOf(), 1
        )
    }
}
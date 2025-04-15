package p25xx

import util.expect

fun main() {
    class Solution {
        fun countGood(nums: IntArray, k: Int): Long {
            var left = 0
            var right = -1

            val counts = hashMapOf<Int, Int>()

            var result = 0L

            var count = 0
            while (left < nums.size) {
                while (count < k && right < nums.lastIndex) {
                    right++

                    nums[right].also {
                        counts[it] = (counts[it] ?: 0) + 1
                        counts[it]?.also {
                            count += it - 1
                        }
                    }
                }

                if (count < k) {
                    break
                }
                result += nums.size - right

                nums[left++].also {
                    counts[it] = (counts[it] ?: 0) - 1
                    counts[it]?.also {
                        count -= it
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().countGood(
            intArrayOf(3, 1, 4, 3, 2, 2, 4), 2
        )
    }
}
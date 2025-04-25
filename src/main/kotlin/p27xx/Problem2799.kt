package p27xx

import util.expect

fun main() {
    class Solution {
        fun countCompleteSubarrays(nums: IntArray): Int {
            val counts = hashMapOf<Int, Int>()
            nums.forEach {
                counts[it] = 0
            }

            val target = counts.size
            counts.clear()

            var left = -1
            var right = -1

            var result = 0
            while (left < nums.size) {
                nums.getOrNull(left++)?.also { n ->
                    counts[n]?.also {
                        if (it == 1) {
                            counts -= n
                        } else {
                            counts[n] = it - 1
                        }
                    }
                }

                while (right < nums.lastIndex && counts.size < target) {
                    right++

                    counts[nums[right]] = (counts[nums[right]] ?: 0) + 1
                }

                if (counts.size == target) {
                    result += nums.size - right
                } else {
                    break
                }
            }

            return result
        }
    }

    expect {
        Solution().countCompleteSubarrays(
            intArrayOf(5, 5, 5, 5)
        )
    }
}

package p25xx

import util.expect

fun main() {
    class Solution {
        fun countQuadruplets(nums: IntArray): Long {
            val pre = LongArray(nums.size + 1)
            var result: Long = 0
            for (j in nums.indices) {
                var suf = 0
                for (k in nums.lastIndex downTo j + 1) {
                    if (nums[j] > nums[k]) {
                        result += pre[nums[k]] * suf
                    } else {
                        ++suf
                    }
                }
                for (x in nums[j] + 1..nums.size) {
                    ++pre[x]
                }
            }
            return result
        }
    }

    expect {
        Solution().countQuadruplets(
            intArrayOf(1, 3, 2, 4, 5)
        )
    }
}
package p31xx

import util.expect

fun main() {
    class Solution {
        fun medianOfUniquenessArray(nums: IntArray): Int {
            val n = nums.size
            val median = (n.toLong() * (n + 1) / 2 + 1) / 2
            var res = 0
            var lo = 1
            var hi = n
            while (lo <= hi) {
                val mid = (lo + hi) / 2
                if (check(nums, mid, median)) {
                    res = mid
                    hi = mid - 1
                } else {
                    lo = mid + 1
                }
            }
            return res
        }

        fun check(nums: IntArray, t: Int, median: Long): Boolean {
            val cnt = hashMapOf<Int, Int>()
            var tot: Long = 0
            var i = 0
            var j = 0
            while (i < nums.size) {
                cnt[nums[i]] = (cnt[nums[i]] ?: 0) + 1
                while (cnt.size > t) {
                    cnt[nums[j]] = cnt[nums[j]]!! - 1
                    if (cnt[nums[j]] == 0) {
                        cnt.remove(nums[j])
                    }
                    j++
                }
                tot += (i - j + 1).toLong()
                i++
            }
            return tot >= median
        }
    }

    expect {
        Solution().medianOfUniquenessArray(
            intArrayOf()
        )
    }
}

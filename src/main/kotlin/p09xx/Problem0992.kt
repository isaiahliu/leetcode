package p09xx

import util.expect

fun main() {
    class Solution {
        fun subarraysWithKDistinct(nums: IntArray, k: Int): Int {
            var result = 0

            val counts: MutableMap<Int, Int> = hashMapOf()

            fun inc(num: Int) {
                counts[num] = (counts[num] ?: 0) + 1
            }

            fun dec(num: Int) {
                counts[num]?.also {
                    if (it == 1) {
                        counts.remove(num)
                    } else {
                        counts[num] = it - 1
                    }
                }
            }

            var l1 = 0
            var l2 = 0

            nums.forEach {
                inc(it)

                if (counts.size > k) {
                    while (counts.size > k) {
                        dec(nums[l2++])
                    }
                    l1 = l2
                }

                if (counts.size == k) {
                    while (counts.size == k) {
                        dec(nums[l2++])
                    }

                    result += l2 - l1

                    inc(nums[--l2])
                }
            }
            return result
        }
    }

    expect {
        Solution().subarraysWithKDistinct(
            intArrayOf(1, 2, 1, 3, 4), 3
        )
    }
}
